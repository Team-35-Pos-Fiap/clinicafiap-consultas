package br.com.clinicafiap.entities.db;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.clinicafiap.entities.enums.StatusConsulta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consulta")
@Getter
@Setter
public class ConsultaDb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
    
    @Column(name = "id_medico", nullable = false)
	private UUID idMedico;
    
    @Column(name = "id_paciente", nullable = false)
	private UUID idPaciente;
    
    @Column(name = "id_usuario_criacao", nullable = false)
	private UUID idUsuarioCriacao;
    
    @Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;
	
    @Column(name = "id_usuario_atualizacao", nullable = true)
    private UUID idUsuarioAtualizacao;
    
    @Column(name = "data_atualizacao", nullable = true)
	private LocalDateTime dataAtualizacao;

    @Column(name = "data_consulta", nullable = false)
    private LocalDateTime dataConsulta;
    
	@Enumerated(EnumType.STRING)
	private StatusConsulta status;
}
