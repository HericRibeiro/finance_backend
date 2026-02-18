package com.finance.finance.infrastructure.security;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    
    private final String secretKey = System.getenv("JWT_SECRET");

    private final long expirationMs = Long.parseLong(System.getenv("JWT_EXPIRATION_MS"));
}
