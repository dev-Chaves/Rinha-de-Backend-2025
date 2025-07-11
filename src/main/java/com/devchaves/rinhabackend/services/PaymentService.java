package com.devchaves.rinhabackend.services;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.devchaves.rinhabackend.config.QueueConfig;
import com.devchaves.rinhabackend.dto.PaymentDTO;
import com.devchaves.rinhabackend.dto.PaymentRespondeDTO;
import com.devchaves.rinhabackend.repositoy.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final RabbitTemplate rabbitTemplate;

    public PaymentService(PaymentRepository paymentRepository, RabbitTemplate rabbitTemplate) {
        this.paymentRepository = paymentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public PaymentRespondeDTO processPayment(PaymentDTO paymentDTO) {

        UUID id = UUID.randomUUID();
        BigDecimal amount = paymentDTO.amount();
        PaymentRespondeDTO response = new PaymentRespondeDTO(id, amount);

        rabbitTemplate.convertAndSend(QueueConfig.paymentQueue, response);

        return response;
    }

}
