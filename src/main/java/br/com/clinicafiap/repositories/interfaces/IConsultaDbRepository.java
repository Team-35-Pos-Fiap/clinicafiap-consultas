package br.com.clinicafiap.repositories.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.clinicafiap.entities.db.ConsultaDb;

public interface IConsultaDbRepository extends JpaRepository<ConsultaDb, UUID>{
	List<ConsultaDb> findAllByIdPaciente(UUID idPaciente);
	List<ConsultaDb> findAllByIdMedico(UUID idMedico);
	boolean existsByIdPacienteAndDataConsulta(UUID id, LocalDateTime data);
	boolean existsByIdMedicoAndDataConsulta(UUID id, LocalDateTime data);

	@Query("select c "
		+  "  from ConsultaDb c "
		+  " where function('date', c.dataConsulta) between current_date + 1 DAY and current_date + 3 DAY "
		+  "   and c.status = 'AGENDADA'")
	List<ConsultaDb> buscarConsultasFuturas();
}