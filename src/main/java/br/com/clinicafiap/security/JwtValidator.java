package br.com.clinicafiap.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PublicKey;

@Component
public class JwtValidator {

    private final JwksClient jwksClient;

    public JwtValidator(JwksClient jwksClient) {
        this.jwksClient = jwksClient;
    }

    public Jws<Claims> parse(String token) {
        PublicKey key = jwksClient.fetchKey();
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token);
    }
}
