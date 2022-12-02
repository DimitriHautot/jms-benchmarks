package com.emasphere.benchmarks.messaging.rabbit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;

import java.util.Random;

@SpringBootApplication
public class RabbitBenchConsumerApplication {

	private static final Logger logger = LoggerFactory.getLogger(RabbitBenchConsumerApplication.class);

	private final Random random = new Random();

	private static final long sleepLowerBoundary = 2_000L;
	private static final int sleepMaximumDelta = 1_000;

	public static void main(String[] args) {
		SpringApplication.run(RabbitBenchConsumerApplication.class, args);
	}

	@RabbitListener(queues = {
			"c01.unified-job.log-00", "c02.unified-job.log-00",
			"c01.unified-job.log-01", "c02.unified-job.log-01",
			"c01.unified-job.log-02", "c02.unified-job.log-02",
			"c01.unified-job.log-03", "c02.unified-job.log-03",
			"c01.unified-job.log-04", "c02.unified-job.log-04",
			"c01.unified-job.log-05", "c02.unified-job.log-05",
			"c01.unified-job.log-06", "c02.unified-job.log-06",
			"c01.unified-job.log-07", "c02.unified-job.log-07",
			"c01.unified-job.log-08", "c02.unified-job.log-08",
			"c01.unified-job.log-09", "c02.unified-job.log-09"
	}, concurrency = "2")
	public void read(String message, @Header(name = AmqpHeaders.CONSUMER_QUEUE) String destination) throws InterruptedException {
		Thread.sleep(sleepLowerBoundary + random.nextInt(sleepMaximumDelta));
		logger.info("Read message [{}] from {}", message, destination);
	}
}
