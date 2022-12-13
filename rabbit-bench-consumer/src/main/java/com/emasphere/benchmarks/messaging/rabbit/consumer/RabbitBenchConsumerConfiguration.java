package com.emasphere.benchmarks.messaging.rabbit.consumer;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitBenchConsumerConfiguration {

    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setChannelTransacted(true);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setPrefetchCount(1);
        return factory;
    }

    @Bean
    public Binding c01binding00() {
        return new Binding("c01.unified-job.log-00", DestinationType.QUEUE, "amq.topic", "unified-job.log-00", null);
    }
    @Bean
    public Binding c02binding00() {
        return new Binding("c02.unified-job.log-00", DestinationType.QUEUE, "amq.topic", "unified-job.log-00", null);
    }
    @Bean
    public Queue c01queue00() {
        return new Queue("c01.unified-job.log-00");
    }
    @Bean
    public Queue c02queue00() {
        return new Queue("c02.unified-job.log-00");
    }
    @Bean
    public Binding c01binding01() {
        return new Binding("c01.unified-job.log-01", DestinationType.QUEUE, "amq.topic", "unified-job.log-01", null);
    }
    @Bean
    public Binding c02binding01() {
        return new Binding("c02.unified-job.log-01", DestinationType.QUEUE, "amq.topic", "unified-job.log-01", null);
    }
    @Bean
    public Queue c01queue01() {
        return new Queue("c01.unified-job.log-01");
    }
    @Bean
    public Queue c02queue01() {
        return new Queue("c02.unified-job.log-01");
    }
    @Bean
    public Binding c01binding02() {
        return new Binding("c01.unified-job.log-02", DestinationType.QUEUE, "amq.topic", "unified-job.log-02", null);
    }
    @Bean
    public Binding c02binding02() {
        return new Binding("c02.unified-job.log-02", DestinationType.QUEUE, "amq.topic", "unified-job.log-02", null);
    }
    @Bean
    public Queue c01queue02() {
        return new Queue("c01.unified-job.log-02");
    }
    @Bean
    public Queue c02queue02() {
        return new Queue("c02.unified-job.log-02");
    }
    @Bean
    public Binding c01binding03() {
        return new Binding("c01.unified-job.log-03", DestinationType.QUEUE, "amq.topic", "unified-job.log-03", null);
    }
    @Bean
    public Binding c02binding03() {
        return new Binding("c02.unified-job.log-03", DestinationType.QUEUE, "amq.topic", "unified-job.log-03", null);
    }
    @Bean
    public Queue c01queue03() {
        return new Queue("c01.unified-job.log-03");
    }
    @Bean
    public Queue c02queue03() {
        return new Queue("c02.unified-job.log-03");
    }
    @Bean
    public Binding c01binding04() {
        return new Binding("c01.unified-job.log-04", DestinationType.QUEUE, "amq.topic", "unified-job.log-04", null);
    }
    @Bean
    public Binding c02binding04() {
        return new Binding("c02.unified-job.log-04", DestinationType.QUEUE, "amq.topic", "unified-job.log-04", null);
    }
    @Bean
    public Queue c01queue04() {
        return new Queue("c01.unified-job.log-04");
    }
    @Bean
    public Queue c02queue04() {
        return new Queue("c02.unified-job.log-04");
    }
    @Bean
    public Binding c01binding05() {
        return new Binding("c01.unified-job.log-05", DestinationType.QUEUE, "amq.topic", "unified-job.log-05", null);
    }
    @Bean
    public Binding c02binding05() {
        return new Binding("c02.unified-job.log-05", DestinationType.QUEUE, "amq.topic", "unified-job.log-05", null);
    }
    @Bean
    public Queue c01queue05() {
        return new Queue("c01.unified-job.log-05");
    }
    @Bean
    public Queue c02queue05() {
        return new Queue("c02.unified-job.log-05");
    }
    @Bean
    public Binding c01binding06() {
        return new Binding("c01.unified-job.log-06", DestinationType.QUEUE, "amq.topic", "unified-job.log-06", null);
    }
    @Bean
    public Binding c02binding06() {
        return new Binding("c02.unified-job.log-06", DestinationType.QUEUE, "amq.topic", "unified-job.log-06", null);
    }
    @Bean
    public Queue c01queue06() {
        return new Queue("c01.unified-job.log-06");
    }
    @Bean
    public Queue c02queue06() {
        return new Queue("c02.unified-job.log-06");
    }
    @Bean
    public Binding c01binding07() {
        return new Binding("c01.unified-job.log-07", DestinationType.QUEUE, "amq.topic", "unified-job.log-07", null);
    }
    @Bean
    public Binding c02binding07() {
        return new Binding("c02.unified-job.log-07", DestinationType.QUEUE, "amq.topic", "unified-job.log-07", null);
    }
    @Bean
    public Queue c01queue07() {
        return new Queue("c01.unified-job.log-07");
    }
    @Bean
    public Queue c02queue07() {
        return new Queue("c02.unified-job.log-07");
    }
    @Bean
    public Binding c01binding08() {
        return new Binding("c01.unified-job.log-08", DestinationType.QUEUE, "amq.topic", "unified-job.log-08", null);
    }
    @Bean
    public Binding c02binding08() {
        return new Binding("c02.unified-job.log-08", DestinationType.QUEUE, "amq.topic", "unified-job.log-08", null);
    }
    @Bean
    public Queue c01queue08() {
        return new Queue("c01.unified-job.log-08");
    }
    @Bean
    public Queue c02queue08() {
        return new Queue("c02.unified-job.log-08");
    }
    @Bean
    public Binding c01binding09() {
        return new Binding("c01.unified-job.log-09", DestinationType.QUEUE, "amq.topic", "unified-job.log-09", null);
    }
    @Bean
    public Binding c02binding09() {
        return new Binding("c02.unified-job.log-09", DestinationType.QUEUE, "amq.topic", "unified-job.log-09", null);
    }
    @Bean
    public Queue c01queue09() {
        return new Queue("c01.unified-job.log-09");
    }
    @Bean
    public Queue c02queue09() {
        return new Queue("c02.unified-job.log-09");
    }
    @Bean
    public Binding allEventsBinding() {
        return new Binding("com.emasphere.integration.queue.all.events", DestinationType.QUEUE, "amq.topic", "#", null);
    }
    @Bean
    public Queue allEventsQueue() {
        return new Queue("com.emasphere.integration.queue.all.events");
    }
}
