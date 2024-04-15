package com.reign.sgonzales.betest.service.impl;

import com.reign.sgonzales.betest.service.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JWTServiceImpl implements JWTService {
    private final Integer expirationTime;
    private final String secretKey;

    public JWTServiceImpl(@Value("${app.security.jwt.secret}") String secretKeyString,
            @Value("${app.security.jwt.expiration}") Integer expirationTime) {
        this.expirationTime = expirationTime;
        this.secretKey = secretKeyString;
    }

    @Override
    public String getJWTToken(String user, String role) {
        return Jwts.builder().setId("ReignJWT").setSubject(user).claim("authorities", role).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS512).compact();
    }

    @Override
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build().parseClaimsJws(token);
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
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public String getRole(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build()
                .parseClaimsJws(token).getBody().get("authorities")
                .toString();
    }
}
