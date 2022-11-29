package com.emasphere.jmsbench;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Topic;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Task implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    private final Random random = new Random();
    private final int count;
    private final List<Topic> topics;
    private final JmsTemplate jmsTemplate;
    private final int threadNumber;

    public Task(int count, List<Topic> topics, JmsTemplate jmsTemplate, int threadNumber) {
        this.count = count;
        this.topics = topics;
        this.jmsTemplate = jmsTemplate;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            Topic topic = topics.get(random.nextInt(10));
            jmsTemplate.convertAndSend(topic, String.format("%05d - ", i).concat(tenUuids()));

            // uncomment to build the gruyère
            jmsTemplate.convertAndSend("nle.storage", String.format("%05d - ", i).concat(tenUuids()));

            logger.info("{} published 2 messages ({} and 🧀)", threadNumber, topic);
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
