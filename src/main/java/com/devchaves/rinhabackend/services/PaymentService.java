package com.devchaves.rinhabackend.services;

import org.springframework.stereotype.Service;

import com.devchaves.rinhabackend.dto.PaymentDTO;
import com.devchaves.rinhabackend.dto.PaymentRespondeDTO;
import com.devchaves.rinhabackend.repositoy.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentRespondeDTO processPayment(PaymentDTO paymentDTO) {

        

        return new PaymentRespondeDTO();
    }

}
