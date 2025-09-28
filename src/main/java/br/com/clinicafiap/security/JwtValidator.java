package br.com.clinicafiap.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PublicKey;

@Component
public class JwtValidator {

    private final PublicKey publicKey;
    private final String expectedIssuer;

    public JwtValidator(PublicKey publicKey,
                        @Value("${security.jwt.issuer:usuarios-service}") String expectedIssuer) {
        this.publicKey = publicKey;
        this.expectedIssuer = expectedIssuer;
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
            .requireIssuer(expectedIssuer)
            .setSigningKey(publicKey)
            .build()
            .parseClaimsJws(token);
    }
}
