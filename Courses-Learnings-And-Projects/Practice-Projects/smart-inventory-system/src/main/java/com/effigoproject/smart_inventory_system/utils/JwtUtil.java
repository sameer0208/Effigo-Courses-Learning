package com.effigoproject.smart_inventory_system.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "yourSecretKey"; // Change this to a secure key

    public String generateToken(UserDetails userDetails) {
        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toSet());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles) // Add roles to token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Set<String> extractRoles(String token) {
        return (Set<String>) extractClaims(token).get("roles");
    }
}
