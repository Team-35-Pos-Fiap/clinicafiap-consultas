package br.com.clinicafiap.entities.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.clinicafiap.entities.enums.StatusConsulta;

public record DadosConsultaDtoResponse(UUID id, LocalDateTime dataConsulta, UsuarioDtoResponse medico, UsuarioDtoResponse paciente, UsuarioDtoResponse usuarioCriacao, StatusConsulta statusConsulta) {

}