package com.totvs.challenge.repositories;

import com.totvs.challenge.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> findAllByUserLogin(String login);

}
