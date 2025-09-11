package br.com.clinicafiap.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.clinicafiap.entities.db.ConsultaDb;
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
}