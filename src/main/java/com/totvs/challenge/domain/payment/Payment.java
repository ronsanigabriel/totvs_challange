package com.totvs.challenge.domain.payment;

import com.totvs.challenge.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "payments")
@Entity(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String description;
    private BigDecimal price;
    private String duedate;
    private String finishdate;
    private PaymentStatus status;

    public Payment(PaymentRequestDTO paymentRequestDTO) {
        this.price = paymentRequestDTO.price();
        this.description = paymentRequestDTO.description();
        this.duedate = paymentRequestDTO.duedate();
        this.finishdate = paymentRequestDTO.finishdate();
        this.status = paymentRequestDTO.status();
        this.user = paymentRequestDTO.user();
    }

}
