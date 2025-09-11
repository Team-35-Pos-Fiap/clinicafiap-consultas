package br.com.clinicafiap.services.interfaces;

import java.util.UUID;

import br.com.clinicafiap.entities.dto.UsuarioDtoResponse;

public interface IUsuarioService {
	UsuarioDtoResponse buscarPorId(UUID id);
}