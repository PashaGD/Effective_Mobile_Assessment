package com.example.bankcards.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "CARDS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Card {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    public UUID id;

    @Column(name = "CARD_NUMBER", nullable = false)
    private String encryptedCardNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private User owner;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    @Column(name = "EXPIRATION_DATE", nullable = false)
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private CardStatus status;


    @Positive(message = "Баланс должен быть больше нуля")
    @Column(name = "BALANCE",nullable = false)
    private BigDecimal balance;
}
