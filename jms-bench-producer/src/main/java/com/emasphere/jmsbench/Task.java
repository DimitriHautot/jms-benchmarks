package com.emasphere.jmsbench;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.Topic;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Task implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    private final Random random = new Random();
    private final int totalMessages;
    private final List<Topic> topics;
    private final JmsTemplate jmsTemplate;
    private final Destination gruyere;
    private final int reportFrequency;

    public Task(int totalMessages, List<Topic> topics, JmsTemplate jmsTemplate, Destination gruyere, int reportFrequency) {
        this.totalMessages = totalMessages;
        this.topics = topics;
        this.jmsTemplate = jmsTemplate;
        this.gruyere = gruyere;
        this.reportFrequency = reportFrequency;
    }

    @Override
    public void run() {
        for (int loop = 0; loop < totalMessages; loop++) {
            Topic topic = topics.get(random.nextInt(topics.size()));
            jmsTemplate.convertAndSend(topic, String.format("%05d - %s", loop, tenUuids()));

            if (gruyere != null) {
                jmsTemplate.convertAndSend(gruyere, String.format("%05d - %s", loop, tenUuids()));
            }

            if (reportFrequency > 0 && loop > 0 && loop % this.reportFrequency == 0) {
                if (gruyere != null) {
                    logger.info("[{}] published {} messages (random topic & 🧀)", Thread.currentThread().getName(), loop);
                } else {
                    logger.info("[{}] published {} messages (random topic)", Thread.currentThread().getName(), loop);
                }
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
