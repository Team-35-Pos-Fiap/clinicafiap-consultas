package br.com.clinicafiap.entities.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.clinicafiap.entities.enums.StatusConsulta;
import br.com.clinicafiap.exceptions.DataConsultaInvalidaException;
import br.com.clinicafiap.exceptions.MedicoInvalidoException;
import br.com.clinicafiap.exceptions.PacienteInvalidoException;
import br.com.clinicafiap.exceptions.UsuarioCriacaoInvalidoException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Consulta {
	private UUID id;
	private UUID idMedico;
	private UUID idPaciente;
	private UUID idUsuarioCriacao;
	private LocalDateTime dataCriacao;
	private UUID idUsuarioAtualizacao;
	private LocalDateTime dataAtualizacao;
	private LocalDateTime dataConsulta;
	private StatusConsulta status;
	
	private Consulta(UUID id, UUID idMedico, UUID idPaciente, UUID idUsuarioCriacao, LocalDateTime dataCriacao, UUID idUsuarioAtualizacao, 
					 LocalDateTime dataAtualizacao, LocalDateTime dataConsulta, StatusConsulta status) {
		this.id = id;
		this.idMedico = idMedico;
		this.idPaciente = idPaciente;
		this.idUsuarioCriacao = idUsuarioCriacao;
		this.dataCriacao = dataCriacao;
		this.idUsuarioAtualizacao = idUsuarioAtualizacao;
		this.dataAtualizacao = dataAtualizacao;
		this.dataConsulta = dataConsulta;
		this.status = status;
	}
	
	public static Consulta criar(UUID id, UUID idMedico, UUID idPaciente, UUID idUsuarioCriacao, LocalDateTime dataCriacao, 
								 UUID idUsuarioAtualizacao, LocalDateTime dataAtualizacao, LocalDateTime dataConsulta, StatusConsulta status){
		
		trataValidacoes(idMedico, idPaciente, idUsuarioCriacao, dataConsulta);
		
		return new Consulta(id, idMedico, idPaciente, idUsuarioCriacao, dataCriacao, idUsuarioAtualizacao, dataAtualizacao, dataConsulta, status);
	}

	private static void trataValidacoes(UUID idMedico, UUID idPaciente, UUID idUsuarioCriacao, LocalDateTime dataConsulta) {
		validarMedico(idMedico);
		validarMedicoEPaciente(idMedico, idPaciente);
		validarPaciente(idPaciente);
		validarUsuarioCriacao(idUsuarioCriacao);
		validarDataConsulta(dataConsulta);		
	}

	private static void validarMedicoEPaciente(UUID idMedico, UUID idPaciente) {
		if(idMedico.equals(idPaciente)) {
			throw new MedicoInvalidoException("O médico e o paciente não podem ter o mesmo identificador.");
		}		
	}

	private static void validarDataConsulta(LocalDateTime dataConsulta) {
		if(dataConsulta == null) {
			throw new DataConsultaInvalidaException("A data da consulta é obrigatória.");
		}
		
		if(dataConsulta.isBefore(LocalDateTime.now())) {
			throw new DataConsultaInvalidaException("A data da consulta não pode ser inferior a data atual.");
		}
	}

	private static void validarUsuarioCriacao(UUID idUsuarioCriacao) {
		if(idUsuarioCriacao == null) {
			throw new UsuarioCriacaoInvalidoException("O id do paciente informado é inválido.");
		}	
	}

	private static void validarPaciente(UUID idPaciente) {
		if(idPaciente == null) {
			throw new PacienteInvalidoException("O id do paciente informado é inválido.");
		}
	}

	private static void validarMedico(UUID idMedico) {
		if(idMedico == null) {
			throw new MedicoInvalidoException("O id do médico informado é inválido.");
		}
	}

	public void atualizarData(LocalDateTime data) {
		this.dataConsulta = data;
	}

	public void cancelarConsulta(UUID id) {
		this.status = StatusConsulta.CANCELADA;
		this.dataAtualizacao = LocalDateTime.now();
		this.idUsuarioAtualizacao = id;
	}
}