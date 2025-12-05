package com.example.POS.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // keep this secret safe in env/properties in production
    private static final String SECRET = "c72c2977a05ed473477a15f9f0b0c1ae2cb834249b10f1e51e4c36e3f97a5e33";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    private final long ACCESS_TOKEN_MS  = 1000L * 60 * 15;          // 15 minutes
    private final long REFRESH_TOKEN_MS = 1000L * 60 * 60 * 24 * 7; // 7 days

    public String generateAccessToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_MS))
                .signWith(SECRET_KEY)   // the library will infer algorithm
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_MS))
                .signWith(SECRET_KEY)
                .compact();
    }

    private Claims parseClaims(String token) {
        // With 0.13.x, Jwts.parser() is the entrypoint; use verifyWith(...) and parseSignedClaims(...)
        Jwt<?, Claims> jwt = Jwts.parser()            // returns builder-like entrypoint in 0.13.x
                .verifyWith(SECRET_KEY)               // set verification key
                .build()
                .parseSignedClaims(token);            // returns a Jwt<Header, Claims>

        return jwt.getPayload();
    }

    public String extractEmail(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    public boolean isTokenExpired(String token) {
        Claims claims = parseClaims(token);
        Date exp = claims.getExpiration();
        return exp.before(new Date());
    }

    public boolean validateToken(String token, String email) {
        try {
            final String tokenEmail = extractEmail(token);
            return (tokenEmail.equals(email) && !isTokenExpired(token));
        } catch (Exception ex) {
            return false;
        }
    }
}
