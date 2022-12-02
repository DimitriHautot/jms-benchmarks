package com.emasphere.benchmarks.messaging.rabbit.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

@SpringBootApplication
@RestController
public class RabbitBenchProducerApplication {
	private static final Logger logger = LoggerFactory.getLogger(RabbitBenchProducerApplication.class);
	private static final String VT_ROOT = "unified-job.log";

	private final RabbitTemplate rabbitTemplate;
	private final List<String> routingKeys;

	public static void main(String[] args) {
		SpringApplication.run(RabbitBenchProducerApplication.class, args);
	}

	public RabbitBenchProducerApplication(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setChannelTransacted(true);
		this.rabbitTemplate.setExchange("amq.topic");
		this.routingKeys = new ArrayList<>(10);
		for (int loop = 0; loop < 10; loop++) {
			this.routingKeys.add(String.format("%s-%02d", VT_ROOT, loop));
		}
		logger.info("Publishing on exchange 'amq.topic' with routing keys {}", routingKeys);
	}

	@GetMapping(value = "/publish")
	public double sendMessages(@RequestParam(required = false, defaultValue = "10") int messagesPerThread,
							   @RequestParam(required = false, defaultValue = "10") int threadCount,
							   @RequestParam(required = false, defaultValue = "0") int reportFrequency) throws InterruptedException {
		logger.info(">>>>>>>>>>");
		logger.info("Publishing {} messages, with {} threads, with {} routing keys - report frequency? {}",
				messagesPerThread * threadCount, threadCount, routingKeys.size(), reportFrequency);

		List<Thread> threads = new ArrayList<>();
		for (int threadNumber = 0; threadNumber < threadCount; threadNumber++) {
			threads.add(
					new Thread(
							new Task(messagesPerThread, routingKeys, rabbitTemplate, reportFrequency),
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
		logger.info("*\trandomly with {} routing keys", routingKeys.size());
		double throughput = (double) messagesPerThread * threadCount * 1_000 / durationInMillis;
		logger.info("*\tthroughput: {} messages per second", throughput);
		logger.info("<<<<<<<<<<");

		return throughput;
	}

}
