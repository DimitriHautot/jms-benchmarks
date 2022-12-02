package com.emasphere.jmsbench;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.Topic;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

@SpringBootApplication
@RestController
@EnableAsync
public class JmsBenchProducerApplication {

    private static final Logger logger = LoggerFactory.getLogger(JmsBenchProducerApplication.class);

    private static final String VT_ROOT = "VT.unified-job.log";
//    private final Topic topic;
    private final Destination gruyere;
    private final List<Topic> topics;
    private final JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication.run(JmsBenchProducerApplication.class, args);
    }

    public JmsBenchProducerApplication(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.jmsTemplate.setSessionTransacted(true);
        this.gruyere = new ActiveMQQueue("gruyere");
//        this.topic = new ActiveMQTopic(vtRoot);
//        logger.info("Publishing on topic {}", topic);
        this.topics = new ArrayList<>(10);
        for (int loop = 0; loop < 10; loop++) {
            this.topics.add(new ActiveMQTopic(String.format("%s-%02d", VT_ROOT, loop)));
        }
        logger.info("Publishing on topics {}", topics);
    }

    @GetMapping(value = "/publish")
    public double sendMessages(@RequestParam(required = false, defaultValue = "10") int messagesPerThread,
                             @RequestParam(required = false, defaultValue = "10") int threadCount,
                             @RequestParam(required = false, defaultValue = "false") boolean useGruyere,
                             @RequestParam(required = false, defaultValue = "0") int reportFrequency) throws InterruptedException {
        logger.info(">>>>>>>>>>");
        logger.info("Publishing {} messages, with {} threads, with{} gruyère, on {} topics - report frequency? {}",
                messagesPerThread * threadCount, threadCount, useGruyere ? "" : "out", topics.size(), reportFrequency);

        List<Thread> threads = new ArrayList<>();
        for (int threadNumber = 0; threadNumber < threadCount; threadNumber++) {
            threads.add(
                    new Thread(
                            new Task(messagesPerThread, topics, jmsTemplate, useGruyere ? this.gruyere : null, reportFrequency),
                            String.format("Worker-%02d", threadNumber))
            );
        }

        LocalTime startPublishing = now();
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long durationInMillis = Duration.between(startPublishing, now()).toMillis();
        long durationInSeconds = Duration.between(startPublishing, now()).toSeconds();
        String niceDuration = String.format("%d:%02d:%02d", durationInSeconds / 3600, (durationInSeconds % 3600) / 60, durationInSeconds % 60);
        logger.info("----------");
        logger.info("Published {} messages in {}", messagesPerThread * threadCount, niceDuration);
        logger.info("*\t{} threads", threadCount);
        logger.info("*\t{} messages per thread", messagesPerThread);
        logger.info("*\trandomly on {} topics", topics.size());
        double throughput = (double) messagesPerThread * threadCount * 1_000 / durationInMillis;
        logger.info("*\tthroughput: {} messages per second", throughput);
        logger.info("<<<<<<<<<<");

        return throughput;
    }
}
