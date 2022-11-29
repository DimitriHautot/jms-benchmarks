package com.emasphere.jmsbench;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

@Configuration
public class JmsBenchConsumerConfiguration {

    @Bean
    public JmsListenerContainerFactory noConverterJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1");
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);

        return factory;
    }

    @Bean
    public ActiveMQQueue queue() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log");
    }
    @Bean
    public ActiveMQQueue queue00() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log-00");
    }
    @Bean
    public ActiveMQQueue queue01() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log-01");
    }
    @Bean
    public ActiveMQQueue queue02() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log-02");
    }
    @Bean
    public ActiveMQQueue queue03() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log-03");
    }
    @Bean
    public ActiveMQQueue queue04() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log-04");
    }
    @Bean
    public ActiveMQQueue queue05() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log-05");
    }
    @Bean
    public ActiveMQQueue queue06() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log-06");
    }
    @Bean
    public ActiveMQQueue queue07() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log-07");
    }
    @Bean
    public ActiveMQQueue queue08() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log-08");
    }
    @Bean
    public ActiveMQQueue queue09() {
        return new ActiveMQQueue("C.jms-bench-consumer.VT.unified-job.log-09");
    }
}
