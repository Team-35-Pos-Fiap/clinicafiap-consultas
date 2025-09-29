package br.com.clinicafiap.entities.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.clinicafiap.entities.enums.StatusConsulta;

public record DadosConsultaDtoResponse(UUID id, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataConsulta, UsuarioDto medico, UsuarioDto paciente, UsuarioDto usuarioCriacao, StatusConsulta statusConsulta) {

}