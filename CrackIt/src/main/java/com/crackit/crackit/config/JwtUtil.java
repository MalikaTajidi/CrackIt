package com.crackit.crackit.config;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret; // Loaded from environment variables .env file

    @Value("${jwt.expiration}")
    private long expirationTime; // Loaded in milliseconds

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Generates a JWT token with the user's email.
     * @param email User's email.
     * @return Signed JWT token.
     */
    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(email) // Set the email as the subject
                .setIssuedAt(now) // Set issued date
                .setExpiration(expiryDate) // Set expiration date
                .signWith(secretKey, SignatureAlgorithm.HS256) // Sign with the secret key
                .compact();
    }

    /**
     * Extracts the email (subject) from the JWT token.
     * @param token JWT token.
     * @return Email address.
     */
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    /**
     * Validates the token and checks if the email matches and the token isn't expired.
     * @param token JWT token.
     * @param email User's email to verify.
     * @return true if valid, false otherwise.
     */
    public boolean validateToken(String token, String email) {
        try {
            return email.equals(extractEmail(token)) && !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Invalid token
        }
    }

    /**
     * Extracts claims from the token.
     * @param token JWT token.
     * @return Claims object.
     */
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if the token is expired.
     * @param token JWT token.
     * @return true if expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}


