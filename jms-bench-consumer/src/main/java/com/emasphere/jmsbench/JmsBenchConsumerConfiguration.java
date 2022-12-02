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
//        factory.setConcurrency("1");
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);

        return factory;
    }

//    @Bean
//    public ActiveMQQueue c01queue() {
//        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log");
//    }
    @Bean
    public ActiveMQQueue c01queue00() {
        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log-00");
    }
    @Bean
    public ActiveMQQueue c01queue01() {
        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log-01");
    }
    @Bean
    public ActiveMQQueue c01queue02() {
        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log-02");
    }
    @Bean
    public ActiveMQQueue c01queue03() {
        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log-03");
    }
    @Bean
    public ActiveMQQueue c01queue04() {
        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log-04");
    }
    @Bean
    public ActiveMQQueue c01queue05() {
        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log-05");
    }
    @Bean
    public ActiveMQQueue c01queue06() {
        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log-06");
    }
    @Bean
    public ActiveMQQueue c01queue07() {
        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log-07");
    }
    @Bean
    public ActiveMQQueue c01queue08() {
        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log-08");
    }
    @Bean
    public ActiveMQQueue c01queue09() {
        return new ActiveMQQueue("C.jms-bench-consumer-01.VT.unified-job.log-09");
    }
//    @Bean
//    public ActiveMQQueue c02queue() {
//        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log");
//    }
    @Bean
    public ActiveMQQueue c02queue00() {
        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log-00");
    }
    @Bean
    public ActiveMQQueue c02queue01() {
        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log-01");
    }
    @Bean
    public ActiveMQQueue c02queue02() {
        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log-02");
    }
    @Bean
    public ActiveMQQueue c02queue03() {
        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log-03");
    }
    @Bean
    public ActiveMQQueue c02queue04() {
        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log-04");
    }
    @Bean
    public ActiveMQQueue c02queue05() {
        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log-05");
    }
    @Bean
    public ActiveMQQueue c02queue06() {
        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log-06");
    }
    @Bean
    public ActiveMQQueue c02queue07() {
        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log-07");
    }
    @Bean
    public ActiveMQQueue c02queue08() {
        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log-08");
    }
    @Bean
    public ActiveMQQueue c02queue09() {
        return new ActiveMQQueue("C.jms-bench-consumer-02.VT.unified-job.log-09");
    }
}
