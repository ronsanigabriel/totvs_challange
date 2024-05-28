package com.totvs.challenge.domain.payment;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    PENDENTE("pendente"),
    PAGO("pago"),
    ATRASO("atraso");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

}
