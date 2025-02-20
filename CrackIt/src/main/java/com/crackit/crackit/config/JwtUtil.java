package com.crackit.crackit.config;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
//import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {


    //@Value("${jwt.expiration:36000000}")
    private static final  long expirationTime = 36000000; // Loaded in milliseconds

    //private SecretKey secretKey;

    //@PostConstruct
    //public void init() {
     //   this.secretKey = Jwts.SIG.HS256.key().build();
   // }
  // private static final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    private static final String SECRET_KEY = "E0gBPl63QkWxOgoxP9XKKeRXboltmdbrq2DmtMKWfUWp2TwHjjot3sV8p397a4gi";
    private static final SecretKey JWT_SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    /**
     * Generates a JWT token with the user's email.
     * @param email User's email.
     * @return Signed JWT token.
     */
    
    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
        .subject(email) // Use subject() instead of setSubject()
        .issuedAt(now)
        .expiration(expiryDate)
        .signWith(JWT_SECRET_KEY, Jwts.SIG.HS256) // Updated signing method
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
        .verifyWith(JWT_SECRET_KEY) // Replaces setSigningKey()
        .build()
        .parseSignedClaims(token)
        .getPayload();

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


