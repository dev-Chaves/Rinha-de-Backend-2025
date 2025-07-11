package com.devchaves.rinhabackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devchaves.rinhabackend.dto.PaymentDTO;
import com.devchaves.rinhabackend.dto.PaymentRespondeDTO;
import com.devchaves.rinhabackend.services.PaymentService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentRespondeDTO> paymentPost(@RequestBody PaymentDTO dto) {

        PaymentRespondeDTO response = paymentService.processPayment(dto);

        return ResponseEntity.ok(response);
    }

}
