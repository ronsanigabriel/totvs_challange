package com.totvs.challenge.controllers;

import com.totvs.challenge.domain.payment.Payment;
import com.totvs.challenge.domain.payment.PaymentRequestDTO;
import com.totvs.challenge.domain.payment.PaymentResponseDTO;
import com.totvs.challenge.domain.user.User;
import com.totvs.challenge.repositories.PaymentRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("payment")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> postProduct(@RequestBody @Valid PaymentRequestDTO paymentRequestDTO) {
        Payment newProduct = new Payment(paymentRequestDTO);

        this.paymentRepository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allBills")
    public ResponseEntity<List<PaymentResponseDTO>> getAllBills() {
        List<PaymentResponseDTO> billsList = this.paymentRepository.findAll().stream().map(PaymentResponseDTO::new).toList();
        return ResponseEntity.ok(billsList);
    }

    @GetMapping("/getMyBills")
    public ResponseEntity<List<PaymentResponseDTO>> getMyBills(@AuthenticationPrincipal UserDetails userDetails) {
        String userLogin = ((User) userDetails).getLogin();
        List<Payment> userBills = paymentRepository.findAllByUserLogin(userLogin);
        List<PaymentResponseDTO> responseDTOs = userBills.stream().map(PaymentResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

}
