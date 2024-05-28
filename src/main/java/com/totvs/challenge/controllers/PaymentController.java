package com.totvs.challenge.controllers;

import com.totvs.challenge._shared.FileReaderUtils;
import com.totvs.challenge.domain.payment.Payment;
import com.totvs.challenge.domain.payment.PaymentRequestDTO;
import com.totvs.challenge.domain.payment.PaymentResponseDTO;
import com.totvs.challenge.domain.payment.PaymentStatus;
import com.totvs.challenge.domain.user.User;
import com.totvs.challenge.repositories.PaymentRepository;
import com.totvs.challenge.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("payment")
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentController(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/import-bills")
    public ResponseEntity<String> importBills() {
        try {
            List<String[]> records = FileReaderUtils.readCSV(new File("src/main/resources/data/import-payments.csv"));
            records.forEach(payment -> {
                User user = (User) userRepository.findByLogin(payment[0]);
                if (user == null) {
                    throw new RuntimeException("User not found with login: " + payment[0]);
                }
                String description = payment[1];
                BigDecimal price = new BigDecimal(payment[2]);
                String dueDate = payment[3];
                String finishDate = payment[4];
                PaymentStatus status = PaymentStatus.valueOf(payment[5]);
                Payment newPayment = new Payment(new PaymentRequestDTO(user, description, price, status, dueDate, finishDate));
                paymentRepository.save(newPayment);
            });
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @PostMapping("/registerNewBill")
    public ResponseEntity<String> registerNewBill(@RequestBody @Valid PaymentRequestDTO paymentRequestDTO) {
        Payment newProduct = new Payment(paymentRequestDTO);
        this.paymentRepository.save(newProduct);
        return ResponseEntity.ok("New bill added to queue");
    }

    @PutMapping("/payBill")
    public ResponseEntity<Void> payBill(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Payment paymentDetails) {
        String userLogin = ((User) userDetails).getLogin();
        Payment existingPayment = paymentRepository.findById(paymentDetails.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
        if (!existingPayment.getUser().getLogin().equals(userLogin)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to update this payment");
        }
        existingPayment.setStatus(PaymentStatus.PAGO);
        existingPayment.setFinishdate(LocalDate.now().toString());

        paymentRepository.save(existingPayment);
        return ResponseEntity.ok().build();
    }

}
