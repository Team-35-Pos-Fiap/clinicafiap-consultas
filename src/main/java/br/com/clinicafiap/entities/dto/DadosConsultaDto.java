package br.com.clinicafiap.entities.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosConsultaDto(UUID id, UUID idMedico, UUID idPaciente, UUID idUsuarioCriacao, LocalDateTime dataCriacao, UUID idUsuarioAtualizacao, LocalDateTime dataAtualizacao, LocalDateTime dataConsulta) {

}