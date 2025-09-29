package br.com.clinicafiap.entities.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DataConsultaDto(@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
					          LocalDateTime dataConsulta) {

}
