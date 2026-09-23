package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import liquibase.structure.core.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    private SecretKey secretKey;
    @Value("${jwt.access-token-expiration}")
    private Duration accessTokenExpiration;

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(
                secret.getBytes(
                        StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Authentication authentication) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(accessTokenExpiration)))
                .signWith(secretKey)
                .compact();
    }

    public long getAccessTokenExpirationSeconds() {
        return accessTokenExpiration.getSeconds();
    }

    public String extractUsername(String jwtToken) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload()
                .getSubject();

    }

    public boolean isTokenValid(String jwtToken) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jwtToken);
        } catch (RuntimeException e){
            return false;
        }
        return true;
    }
}
