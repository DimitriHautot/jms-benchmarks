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

    @JmsListener(destination = "#{queue.queueName}", concurrency = "2")
    public void read(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    @JmsListener(destination = "#{queue00.queueName}", concurrency = "2")
    public void read00(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    @JmsListener(destination = "#{queue01.queueName}", concurrency = "2")
    public void read01(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    @JmsListener(destination = "#{queue02.queueName}", concurrency = "2")
    public void read02(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    @JmsListener(destination = "#{queue03.queueName}", concurrency = "2")
    public void read03(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    @JmsListener(destination = "#{queue04.queueName}", concurrency = "2")
    public void read04(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    @JmsListener(destination = "#{queue05.queueName}", concurrency = "2")
    public void read05(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    @JmsListener(destination = "#{queue06.queueName}", concurrency = "2")
    public void read06(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    @JmsListener(destination = "#{queue07.queueName}", concurrency = "2")
    public void read07(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    @JmsListener(destination = "#{queue08.queueName}", concurrency = "2")
    public void read08(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    @JmsListener(destination = "#{queue09.queueName}", concurrency = "2")
    public void read09(String message, @Header(name = JmsHeaders.DESTINATION) String destination) throws InterruptedException {
        handle(message, destination);
    }

    private void handle(String message, String destination) throws InterruptedException {
        Thread.sleep(sleepLowerBoundary + random.nextInt(sleepMaximumDelta));
        logger.info("Read message [{}] from {}", message, destination);
    }
}
