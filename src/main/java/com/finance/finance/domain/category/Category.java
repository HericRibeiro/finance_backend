package com.finance.finance.domain.category;


import com.finance.finance.domain.user.User;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;

@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User user;
}
