package com.kurly.hackfesta.security;

import com.kurly.hackfesta.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {

    private static final String SECRET_KEY = "NMA8JPctFuna59f5";

    public String create(User user) {
        // 만료기한 1일
        Date expiryDate = Date.from(Instant.now()
                .plus(1, ChronoUnit.DAYS));

        // JWT Token 생성
        return Jwts.builder()
                // header, secret_key
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                // payload
                .setSubject(user.getEmail())
                .setIssuer("My app")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }

    public String validateAndGetUserId(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
