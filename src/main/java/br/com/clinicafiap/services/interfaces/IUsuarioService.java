package br.com.clinicafiap.services.interfaces;

import java.util.UUID;

import br.com.clinicafiap.entities.dto.UsuarioDto;
import br.com.clinicafiap.grpc.usuario.ValidaUsuariosParaAgendamentoResponse;

public interface IUsuarioService {
	UsuarioDto buscarPorId(UUID id);
	ValidaUsuariosParaAgendamentoResponse validaUsuariosParaAgendamento(UUID idMedico, UUID idPaciente, UUID idUsuarioCriacao);
}