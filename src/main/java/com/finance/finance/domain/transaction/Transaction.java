package com.finance.finance.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.finance.finance.domain.category.Category;
import com.finance.finance.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    private BigDecimal amount;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;
}
