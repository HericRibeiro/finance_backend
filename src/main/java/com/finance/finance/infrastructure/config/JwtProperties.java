package com.finance.finance.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration 
@ConfigurationProperties(prefix = "app") 
public class JwtProperties { 
    private Jwt jwt; 
    public static class Jwt { 
        
        private String secret; 
        private long expiration; 
        
        public String getSecret() { 
            return secret; 
        } 

        public void setSecret(String secret) {
             this.secret = secret; 
        }
        
        public long getExpiration() {
             return expiration; 
        } 
        public void setExpiration(long expiration) {
             this.expiration = expiration; 
        } 
    } 
    
    public Jwt getJwt() {
        return jwt; 
    } 
    
    public void setJwt(Jwt jwt) {
        this.jwt = jwt; 
    } 
}