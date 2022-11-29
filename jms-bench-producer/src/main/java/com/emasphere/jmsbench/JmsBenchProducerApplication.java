package com.emasphere.jmsbench;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.time.LocalTime.now;

@SpringBootApplication
@RestController
@EnableAsync
public class JmsBenchProducerApplication {
    private static final Logger logger = LoggerFactory.getLogger(JmsBenchProducerApplication.class);

    private static final String vtRoot = "VT.unified-job.log";
    private final Topic topic;
    private final List<Topic> topics;
    private final JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication.run(JmsBenchProducerApplication.class, args);
    }

    public JmsBenchProducerApplication(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.jmsTemplate.setSessionTransacted(true);
        this.topic = new ActiveMQTopic(vtRoot);
        logger.info("Publishing on topic {}", topic);
        this.topics = new ArrayList<>(10);
        for (int loop = 0; loop < 10; loop++) {
            this.topics.add(new ActiveMQTopic(String.format("%s-%02d", vtRoot, loop)));
        }
        logger.info("Publishing on topics {}", topics);
    }

    @GetMapping(value = "/temp/jms")
    @Async
    public void sendMessages(@RequestParam(required = false, defaultValue = "10") int count) throws InterruptedException {
        logger.info("Publishing {} messages", count);
        LocalTime startPublishing = now();

        int threadCount = 30;

        List<Thread> threads = new ArrayList<>();
        for (int threadNumber = 0; threadNumber < threadCount; threadNumber++) {
            threads.add(new Thread(new Task(count, topics, jmsTemplate, threadNumber)));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        logger.info("Published {} messages in {} ms", count, Duration.between(startPublishing, now()).toMillis());
    }
}
