package br.com.clinicafiap.graphql.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ConsultaDtoGraphQL {
    private UUID id;
    private UUID idMedico;
    private UUID idPaciente;
    private UUID idUsuarioCriacao;
    private OffsetDateTime dataCriacao;
    private UUID idUsuarioAtualizacao;
    private OffsetDateTime dataAtualizacao;
    private OffsetDateTime dataConsulta;
    private String status;

    public ConsultaDtoGraphQL() {}

    public ConsultaDtoGraphQL(UUID id, UUID idMedico, UUID idPaciente, UUID idUsuarioCriacao,
                              OffsetDateTime dataCriacao, UUID idUsuarioAtualizacao,
                              OffsetDateTime dataAtualizacao, OffsetDateTime dataConsulta, String status) {
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

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public OffsetDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public OffsetDateTime getDataConsulta() {
        return dataConsulta;
    }

    public String getStatus() {
        return status;
    }
}
