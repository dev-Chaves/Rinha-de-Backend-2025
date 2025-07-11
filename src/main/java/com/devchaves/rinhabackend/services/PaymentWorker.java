package com.devchaves.rinhabackend.services;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.devchaves.rinhabackend.dto.PaymentDTO;

@Component
public class PaymentWorker {

    @RabbitListener(queues = "paymentQueue")
    public void receivePayment(PaymentDTO paymentDTO) {
        System.out.println("Received Payment: " + paymentDTO);
    }

}
