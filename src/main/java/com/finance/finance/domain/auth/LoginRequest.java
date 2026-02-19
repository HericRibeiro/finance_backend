package com.finance.finance.domain.auth;

public record LoginRequest(
    String email,
    String password
) {}
