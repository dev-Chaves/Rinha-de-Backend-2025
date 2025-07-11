package com.devchaves.rinhabackend.repositoy;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devchaves.rinhabackend.model.Payments;

public interface PaymentRepository extends JpaRepository<Payments, UUID> {

}
