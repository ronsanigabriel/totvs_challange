package com.totvs.challenge.domain.payment;

import java.math.BigDecimal;

public record PaymentResponseDTO(String username, String description, BigDecimal price, String status) {
    public PaymentResponseDTO(Payment payment) {
        this(payment.getUser().getUsername(), payment.getDescription(), payment.getPrice(), payment.getStatus());
    }
}
