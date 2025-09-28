package br.com.clinicafiap.graphql.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConsultaDtoGraphQL {
    private UUID id;
    private UUID idMedico;
    private UUID idPaciente;
    private UUID idUsuarioCriacao;
    private LocalDateTime dataCriacao;
    private UUID idUsuarioAtualizacao;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataConsulta;
    private String status;

    public ConsultaDtoGraphQL() {}

    public ConsultaDtoGraphQL(UUID id, UUID idMedico, UUID idPaciente, UUID idUsuarioCriacao,
                              LocalDateTime dataCriacao, UUID idUsuarioAtualizacao,
                              LocalDateTime dataAtualizacao, LocalDateTime dataConsulta, String status) {
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

    public UUID getId() {
        return id;
    }

    public UUID getIdMedico() {
        return idMedico;
    }

    public UUID getIdPaciente() {
        return idPaciente;
    }

    public UUID getIdUsuarioCriacao() {
        return idUsuarioCriacao;
    }

    public UUID getIdUsuarioAtualizacao() {
        return idUsuarioAtualizacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public String getStatus() {
        return status;
    }
}
