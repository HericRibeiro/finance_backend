package com.finance.finance.infrastructure.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.finance.finance.infrastructure.config.JwtProperties;

@Service
public class JwtService {

    @Autowired
    private JwtProperties jwtProperties;

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getJwt().getSecret().getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getJwt().getExpiration()))
            .signWith(getSecretKey(), SignatureAlgorithm.HS256)
            .compact();
    }


    // Criar um arquivo DTO para o subject do token, para evitar passar muitos parâmetros
    // Pegar role do banco, assim muda imediatamente quando o usuário for promovido ou rebaixado, sem precisar gerar um novo token
    // Outra vantagem é que se o token vazar, o invasor não tem acesso a informações sensíveis do usuário, como senha ou outros dados pessoais
    public String generateTokenWithUser(TokenSubject subject) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", subject.getId());
        claims.put("email", subject.getEmail());
        claims.put("role", subject.getRole().toString());

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getJwt().getExpiration()))
            .signWith(getSecretKey(), SignatureAlgorithm.HS256)
            .compact();
    }

        public String extractMail(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public Long extractId(String token) {
        return extractClaims(token).get("id", Long.class);
    }

    public boolean validToken(String token, String email) {
        String emailOfToken = extractMail(token);
        return (emailOfToken.equals(email) && !tokenExpiration(token));
    }

    public boolean tokenExpiration(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

}
