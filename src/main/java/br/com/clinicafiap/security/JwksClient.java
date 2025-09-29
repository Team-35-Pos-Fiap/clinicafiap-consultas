package br.com.clinicafiap.security;

import br.com.clinicafiap.exceptions.PublicKeyFetchException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.URI;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class JwksClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String jwksUrl = "http://localhost:8083/usuarios-service/auth/.well-known/jwks.json";

    public PublicKey fetchKey() {
        try {
            log.info("Buscando JWKS em {}", jwksUrl);

            String json = restTemplate.getForObject(URI.create(jwksUrl), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode key = root.get("keys").get(0);

            String n = key.get("n").asText();
            String e = key.get("e").asText();
            log.info("n: {}, e: {}", n, e);
            BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(n));
            BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(e));

            return KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (Exception ex) {
            throw new PublicKeyFetchException("Não foi possível buscar a chave pública do usuarios-service");
        }
    }
}
