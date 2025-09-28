package br.com.clinicafiap.configs;

import br.com.clinicafiap.exceptions.PublicKeyFetchException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Configuration
public class JwksKeyProvider {

    @Bean
    public PublicKey jwtPublicKey() {
        try {
            URL url = new URL("http://usuarios-service/auth/.well-known/jwks.json");
            try (InputStream is = url.openStream()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(is);
                JsonNode key = root.get("keys").get(0); // pega a primeira chave

                String n = key.get("n").asText();
                String e = key.get("e").asText();

                BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(n));
                BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(e));

                RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exponent);
                return KeyFactory.getInstance("RSA").generatePublic(spec);
            }
        } catch (Exception ex) {
            throw new PublicKeyFetchException("Não foi possível buscar a chave pública do usuarios-service");
        }
    }
}
