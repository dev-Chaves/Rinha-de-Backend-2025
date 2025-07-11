package com.devchaves.rinhabackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.AMQP.Queue;

@Configuration
public class QueueConfig {

    public static final String paymentQueue = "work-queue";

    @Bean
    public Queue paymentQueue() {
        return new Queue(paymentQueue, true);
    }

}
