package com.emasphere.benchmarks.messaging.rabbit.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Task implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    private final Random random = new Random();
    private final int totalMessages;
    private final List<String> routingKeys;
    private final RabbitTemplate rabbitTemplate;
    private final int reportFrequency;

    public Task(int totalMessages, List<String> routingKeys, RabbitTemplate rabbitTemplate, int reportFrequency) {
        this.totalMessages = totalMessages;
        this.routingKeys = routingKeys;
        this.rabbitTemplate = rabbitTemplate;
        this.reportFrequency = reportFrequency;
    }

    @Override
    public void run() {
        for (int loop = 0; loop < totalMessages; loop++) {
            String routingKey = routingKeys.get(random.nextInt(routingKeys.size()));
            rabbitTemplate.convertAndSend(routingKey, String.format("%05d - %s", loop, tenUuids()));

            if (reportFrequency > 0 && loop > 0 && loop % this.reportFrequency == 0) {
                logger.info("[{}] published {} messages (random topic)", Thread.currentThread().getName(), loop);
            }
        }
    }

    private String tenUuids() {
        return UUID.randomUUID().toString()
                .concat(UUID.randomUUID().toString())
                .concat(UUID.randomUUID().toString())
                .concat(UUID.randomUUID().toString())
                .concat(UUID.randomUUID().toString())
                .concat(UUID.randomUUID().toString())
                .concat(UUID.randomUUID().toString())
                .concat(UUID.randomUUID().toString())
                .concat(UUID.randomUUID().toString())
                .concat(UUID.randomUUID().toString());
    }
}
