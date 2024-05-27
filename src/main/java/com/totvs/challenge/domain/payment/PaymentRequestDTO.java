package com.totvs.challenge.domain.payment;

import com.totvs.challenge.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        @NotNull
        User user,
        @NotBlank
        String description,
        @NotNull
        BigDecimal price,
        @NotNull
        String status
) {
}
