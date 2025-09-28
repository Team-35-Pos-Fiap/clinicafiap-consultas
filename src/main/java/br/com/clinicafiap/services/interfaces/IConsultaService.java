package br.com.clinicafiap.services.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.clinicafiap.entities.dto.DadosConsultaDtoRequest;
import br.com.clinicafiap.entities.dto.DadosConsultaDtoResponse;

public interface IConsultaService {
	void agendar(DadosConsultaDtoRequest dados);

	List<DadosConsultaDtoResponse> buscarConsultasPorMedico(UUID idMedico);

	List<DadosConsultaDtoResponse> buscarConsultasPorPaciente(UUID idPaciente);

	void atualizarDataConsulta(UUID idConsulta, LocalDateTime data);

	void cancelarConsulta(UUID idConsulta, UUID idUsuarioCancelamento);

	DadosConsultaDtoResponse buscarPorId(UUID idConsulta);
}