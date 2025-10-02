package br.com.clinicafiap.services;

import br.com.clinicafiap.services.interfaces.IUsuarioService;
import br.com.clinicafiap.entities.dto.UsuarioDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class UsuarioService implements IUsuarioService {

    private final RestTemplate restTemplate;

    public UsuarioService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UsuarioDto buscarPorId(UUID id) {
        String url = "http://localhost:8083/usuarios-service/usuarios/" + id;
        return restTemplate.getForObject(url, UsuarioDto.class);
    }
}