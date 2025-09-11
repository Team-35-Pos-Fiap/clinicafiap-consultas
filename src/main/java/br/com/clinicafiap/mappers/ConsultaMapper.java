package br.com.clinicafiap.mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.clinicafiap.entities.db.ConsultaDb;
import br.com.clinicafiap.entities.domain.Consulta;
import br.com.clinicafiap.entities.dto.DadosConsultaDtoRequest;
import br.com.clinicafiap.entities.dto.DadosConsultaDtoResponse;
import br.com.clinicafiap.entities.dto.UsuarioDtoResponse;
import br.com.clinicafiap.entities.enums.StatusConsulta;

public class ConsultaMapper {

	public static Consulta toConsulta(DadosConsultaDtoRequest dados) {
		return Consulta.criar(null, dados.idMedico(), dados.idPaciente(), dados.idUsuarioCriacao(), LocalDateTime.now(), 
							  null, null, dados.dataConsulta(), StatusConsulta.AGENDADA);
	}

	public static ConsultaDb toConsultaDb(Consulta consulta) {
		return new ConsultaDb(consulta.getId(), consulta.getIdMedico(), consulta.getIdPaciente(), consulta.getIdUsuarioCriacao(),
				consulta.getDataCriacao(), consulta.getIdUsuarioAtualizacao(), consulta.getDataAtualizacao(), consulta.getDataConsulta(), consulta.getStatus());
	}

	public static Consulta toConsulta(ConsultaDb consulta) {
		return Consulta.criar(consulta.getId(), consulta.getIdMedico(), consulta.getIdPaciente(), consulta.getIdUsuarioCriacao(),
							  consulta.getDataCriacao(), consulta.getIdUsuarioAtualizacao(), consulta.getDataAtualizacao(), consulta.getDataConsulta(), consulta.getStatus());
	}
	
	public static List<Consulta> toListConsultas(List<ConsultaDb> consultas) {
		return consultas.stream().map(c -> ConsultaMapper.toConsulta(c)).collect(Collectors.toList());
	}

	public static DadosConsultaDtoResponse toDadosConsultaDtoResponse(Consulta consulta, UsuarioDtoResponse medico, UsuarioDtoResponse paciente, UsuarioDtoResponse usuarioCriacao) {
		return null;
	}
}