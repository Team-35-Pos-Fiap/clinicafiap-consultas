package br.com.clinicafiap.services.interfaces;

import java.util.UUID;

import br.com.clinicafiap.entities.dto.UsuarioDto;

public interface IUsuarioService {
	UsuarioDto buscarPorId(UUID id);
}