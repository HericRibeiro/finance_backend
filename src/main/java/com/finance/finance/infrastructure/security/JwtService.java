package com.finance.finance.infrastructure.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import com.finance.finance.infrastructure.config.JwtProperties;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    private Key getKey() {
        return Keys.hmacShaKeyFor(
            jwtProperties.getJwt().getSecret().getBytes()
        );
    }

    public String generateToken(Long id, String email) {
        return Jwts.builder()
            .setSubject(email)
            .claim("id", id)
            .setIssuedAt(new Date())
            .setExpiration(new Date(
                System.currentTimeMillis() + jwtProperties.getJwt().getExpiration()
            ))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }
    
    public Long extractId(String token) {
        return getClaims(token).get("id", Long.class);
    }
    
    public boolean isValid(String token, String email) {
        String emailOfToken = extractEmail(token);
        return (emailOfToken.equals(email) && !isExpired(token));
    }

    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

}
