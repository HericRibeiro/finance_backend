package com.finance.finance.domain.category;


import java.util.UUID;

import com.finance.finance.domain.user.User;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // private String name;

    @ManyToOne
    private User user;
}
