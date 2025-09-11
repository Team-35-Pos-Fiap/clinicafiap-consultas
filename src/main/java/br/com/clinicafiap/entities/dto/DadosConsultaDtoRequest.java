package br.com.clinicafiap.entities.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DadosConsultaDtoRequest(UUID idMedico, 
									  UUID idPaciente, 
									  UUID idUsuarioCriacao, 
									  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
									  LocalDateTime dataConsulta) {

}