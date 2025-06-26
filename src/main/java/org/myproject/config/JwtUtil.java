package org.myproject.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.myproject.dto.SuperUserDto;
import org.myproject.enums.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret:mysecretkeymysecretkeymysecretkeymysecretkey}")
    private String secret;

    @Value("${jwt.expiration:31536000000}") // 1 year in milliseconds
    private long expirationTime;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String phoneNumber, UserRole role , List<Long> familyIds ,Long templeId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        if(familyIds != null)claims.put("familyIds",familyIds);
        if(templeId != null)claims.put("templeId", templeId);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(phoneNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);
    }


    public Long extractTempleId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("templeId", Long.class);
    }

    public List<Long> extractFamilyIds(String token) {
        Claims claims = extractAllClaims(token);
        List<?> familyIds = claims.get("familyIds", List.class);
        return familyIds.stream()
                .map(id -> Long.valueOf(String.valueOf(id)))
                .toList();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
