package br.com.clinicafiap.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.clinicafiap.entities.db.ConsultaDb;
import br.com.clinicafiap.exceptions.ConsultaNaoEncontradaException;
import br.com.clinicafiap.repositories.interfaces.IConsultaDbRepository;
import br.com.clinicafiap.repositories.interfaces.IConsultaRepository;

@Repository
public class ConsultaRepository implements IConsultaRepository {

	private IConsultaDbRepository consultaRepository;

	public ConsultaRepository(IConsultaDbRepository consultaRepository) {
		this.consultaRepository = consultaRepository;
	}
	
	@Override
	public void salvar(ConsultaDb consulta) {
		consultaRepository.save(consulta);	
	}

	@Override
	public List<ConsultaDb> buscarConsultasPorMedico(UUID idMedico) {
		return consultaRepository.findAllByIdMedico(idMedico);
	}

	@Override
	public List<ConsultaDb> buscarConsultasPorPaciente(UUID idPaciente) {
		return consultaRepository.findAllByIdPaciente(idPaciente);
	}

	@Override
	public boolean verificaConsultaPorUsuario(UUID id, LocalDateTime data, boolean isMedico) {
		if(isMedico) {
			return consultaRepository.existsByIdMedicoAndDataConsulta(id, data);
		} else {
			return consultaRepository.existsByIdPacienteAndDataConsulta(id, data);
		}
	}

	@Override
	public ConsultaDb buscarPorId(UUID idConsulta) {
		return consultaRepository.findById(idConsulta)
								 .orElseThrow(() -> new ConsultaNaoEncontradaException("Não foi encontrada nenhuma consulta com o id informado."));
	}

	@Override
	public List<ConsultaDb> buscarConsultasFuturas() {
		return consultaRepository.buscarConsultasFuturas();
	}

	@Override
	public List<ConsultaDb> buscarConsultasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
		return consultaRepository.findAllByDataConsultaBetween(dataInicio, dataFim);
	}
}