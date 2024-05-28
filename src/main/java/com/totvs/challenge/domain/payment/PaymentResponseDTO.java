package com.totvs.challenge.domain.payment;

import java.math.BigDecimal;

public record PaymentResponseDTO(String id, String userlogin, String description, BigDecimal price, String duedate, String finishdate, PaymentStatus status) {
    public PaymentResponseDTO(Payment payment) {
        this(payment.getId(), payment.getUser().getLogin(), payment.getDescription(), payment.getPrice(), payment.getDuedate(), payment.getFinishdate(), payment.getStatus());
    }
}
