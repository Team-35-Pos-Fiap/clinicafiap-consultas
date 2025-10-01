package br.com.clinicafiap.repositories.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.clinicafiap.entities.db.ConsultaDb;

public interface IConsultaRepository {
	void salvar(ConsultaDb consulta);

	List<ConsultaDb> buscarConsultasPorMedico(UUID idMedico);

	List<ConsultaDb> buscarConsultasPorPaciente(UUID idPaciente);

	boolean verificaConsultaPorUsuario(UUID id, LocalDateTime data, boolean isMedico);

	ConsultaDb buscarPorId(UUID idConsulta);

	List<ConsultaDb> buscarConsultasFuturas();

	List<ConsultaDb> buscarConsultasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim);
}