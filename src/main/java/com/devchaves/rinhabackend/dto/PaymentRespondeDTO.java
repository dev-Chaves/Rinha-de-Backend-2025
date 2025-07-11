package com.devchaves.rinhabackend.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRespondeDTO(
    UUID id,
    BigDecimal amount
) {

}
