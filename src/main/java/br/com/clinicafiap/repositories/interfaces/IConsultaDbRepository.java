package br.com.clinicafiap.repositories.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clinicafiap.entities.db.ConsultaDb;

public interface IConsultaDbRepository extends JpaRepository<ConsultaDb, UUID>{
	List<ConsultaDb> findAllByIdPaciente(UUID idPaciente);
	List<ConsultaDb> findAllByIdMedico(UUID idMedico);
}