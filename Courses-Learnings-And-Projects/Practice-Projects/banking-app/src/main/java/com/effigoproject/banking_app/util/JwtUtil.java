package com.effigoproject.banking_app.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "your_secret_key";
    private final long EXPIRATION_TIME = 86400000; // 1 day

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()  // Use the updated parserBuilder() method
                .setSigningKey(SECRET_KEY)  // Set the signing key for validation
                .build()  // Build the parser
                .parseClaimsJws(token)  // Parses the claims from the JWT token
                .getBody();  // Get the claims from the JWT
    }
}
