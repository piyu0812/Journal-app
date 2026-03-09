package net.engineeringdigest.journalApp.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWUtils {

    private String SECRET_KEY = "b3f4a8d9c1e6f7a2b5c9d8e1f3a4b6c7d9e2f1a8b3c6d4e5f7a9b1c3d5e7f8a2";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName) {
        Map<String, ObjectId> claims = new HashMap<>();
        return createToken(claims, userName);
    }
    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public String createToken(Map<String, ObjectId> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("typ", "JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey())
                .compact();

    }
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}
