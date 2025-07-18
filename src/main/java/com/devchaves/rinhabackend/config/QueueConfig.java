package com.devchaves.rinhabackend.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String paymentQueue = "paymentQueue";

    @Bean
    public Queue paymentQueue() {
        return new Queue(paymentQueue, true);
    }

}
