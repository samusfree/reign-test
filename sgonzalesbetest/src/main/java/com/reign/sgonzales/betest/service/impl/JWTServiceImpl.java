package com.reign.sgonzales.betest.service.impl;

import com.reign.sgonzales.betest.service.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Slf4j
public class JWTServiceImpl implements JWTService {
    private final Integer expirationTime;
    private final SecretKey secretKey;

    public JWTServiceImpl(@Value("${app.security.jwt.secret}") String secretKeyString,
            @Value("${app.security.jwt.expiration}") Integer expirationTime) {
        this.expirationTime = expirationTime;
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    @Override
    public String getJWTToken(String user, String role) {
        return Jwts.builder().id("ReignJWT").subject(user).claim("authorities", role).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime)).signWith(secretKey).compact();
    }

    @Override
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException ex) {
            log.error("JWT expired: ", ex);
        } catch (IllegalArgumentException ex) {
            log.error("Token is null, empty or only whitespace", ex);
        } catch (MalformedJwtException ex) {
            log.error("JWT is invalid", ex);
        } catch (Exception ex) {
            log.error("JWT unsupported", ex);
        }
        return false;
    }

    @Override
    public String getUserName(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
    }

    @Override
    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("authorities")
                .toString();
    }
}
