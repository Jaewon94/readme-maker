package com.ai.readmemaker.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class GenerateJwtSecretKey {
    public static void main(String[] args) {
        // HMAC-SHA512 알고리즘에 대해 적절한 길이의 비밀 키를 생성
        var key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

        // 비밀 키를 Base64로 인코딩
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        // Base64 인코딩된 비밀 키를 출력
        System.out.println("Base64 Encoded JWT Secret Key:");
        System.out.println(base64Key);
    }
}