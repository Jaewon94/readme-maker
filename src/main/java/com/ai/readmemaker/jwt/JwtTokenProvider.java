package com.ai.readmemaker.jwt;

import com.ai.readmemaker.user.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class JwtTokenProvider {

    private static final Logger logger = Logger.getLogger(JwtTokenProvider.class.getName());

    private SecretKey key;

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    @PostConstruct
    public void init() {
        try {
            // URL-safe Base64 문자열을 표준 Base64 문자열로 변환
            String base64Secret = jwtSecret
                    .replace('-', '+') // URL-safe Base64의 '-'를 '+'로 변환
                    .replace('_', '/'); // URL-safe Base64의 '_'를 '/'로 변환

            byte[] decodedKey = Base64.getDecoder().decode(base64Secret);

            if (decodedKey.length < 32) { // 256비트 = 32바이트
                throw new IllegalArgumentException("The provided JWT secret key is too short. It must be at least 256 bits (32 bytes).");
            }
            this.key = Keys.hmacShaKeyFor(decodedKey);
            logger.info("JWT secret key initialized from configuration.");

            // 디버깅 로그
            logger.info("key = " + Arrays.toString(decodedKey));
            logger.info("jwtSecret = " + jwtSecret);
            logger.info("jwtExpirationInMs = " + jwtExpirationInMs);
        } catch (Exception e) {
            logger.severe("Failed to initialize JWT secret key: " + e.getMessage());
        }
    }

    public String generateToken(Authentication authentication) {
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // Other methods (token validation, etc.)...
}