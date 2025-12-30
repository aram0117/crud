package com.basic.crud.utill;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private static final long TOKEN_TIME = 60 * 60 * 1000L;
    // 속성
    @Value("${jwt.secretKey}")
    private String secretKeyString;
    private SecretKey key;

    @PostConstruct
    public void init() {
        byte[] decodeSecretKey = Base64.getDecoder().decode(secretKeyString);
        key = Keys.hmacShaKeyFor(decodeSecretKey);
    }

    // 토큰 발급
    public String generationToken(long id, String email) {

        Date now = new Date();

        return Jwts.builder()
                .subject(String.valueOf(id))
                .claim("email", email)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + TOKEN_TIME))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    // 클레임 추출
    public Claims extractToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
