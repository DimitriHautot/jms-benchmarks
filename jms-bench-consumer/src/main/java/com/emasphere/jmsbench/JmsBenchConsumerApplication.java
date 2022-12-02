package com.emasphere.jmsbench;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.Random;

@SpringBootApplication
public class JmsBenchConsumerApplication {
    private static final Logger logger = LoggerFactory.getLogger(JmsBenchConsumerApplication.class);
    private final Random random = new Random();

    private static final long sleepLowerBoundary = 2_000L;
    private static final int sleepMaximumDelta = 1_000;

    public static void main(String[] args) {
        SpringApplication.run(JmsBenchConsumerApplication.class, args);
    }

//    @JmsListener(destination = "#{c01queue.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c01queue00.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c01queue01.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c01queue02.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c01queue03.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c01queue04.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c01queue05.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c01queue06.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c01queue07.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c01queue08.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c01queue09.queueName}", concurrency = "2")
//    @JmsListener(destination = "#{c02queue.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c02queue00.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c02queue01.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c02queue02.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c02queue03.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c02queue04.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c02queue05.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c02queue06.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c02queue07.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c02queue08.queueName}", concurrency = "2")
    @JmsListener(destination = "#{c02queue09.queueName}", concurrency = "2")
    public void read(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        Thread.sleep(sleepLowerBoundary + random.nextInt(sleepMaximumDelta));
        logger.info("Read message [{}] from {}", message, destination);
    }
}
