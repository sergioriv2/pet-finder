package com.example.demo.services;

import com.example.demo.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@AllArgsConstructor
public class JwtService {
    @Autowired
    private Environment env;
    public Map<String, String> generateTokens(User user) {
        Map<String, String> tokensListMap = new HashMap<String, String>();

        tokensListMap.put("accessToken", generateAccessToken(user));
        tokensListMap.put("refreshToken", generateRefreshToken(user));

        return tokensListMap;
    }

    private SecretKey getKey(String keyName) {
        String secret = env.getProperty(keyName);
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateAccessToken(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        // Access JWT Expiration
        final int minutesToExpire = 10;
        final int msToMinutes = 1000 * 60;

            return Jwts
                    .builder()
                    .claims(extraClaims)
                    .subject(user.getId().toString())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis()+ (minutesToExpire * msToMinutes)))
                    .signWith(getKey("jwt.accessKey"))
                    .compact();
    }

    private String generateRefreshToken(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        // Refresh JWT Expiration
        final int minutesToExpire = 160;
        final int msToMinutes = 1000 * 60;

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ (minutesToExpire * msToMinutes)))
                .signWith(getKey("jwt.refreshKey"))
                .compact();
    }
}
