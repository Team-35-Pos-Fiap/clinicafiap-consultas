package br.com.clinicafiap.graphql.mappers;

import br.com.clinicafiap.entities.domain.Consulta;
import br.com.clinicafiap.graphql.dto.ConsultaDtoGraphQL;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class ConsultaGraphQLMapper {
    private static final ZoneId ZONE = ZoneId.of("America/Sao_Paulo");

    public static ConsultaDtoGraphQL toConsultaDtoGraphQL(Consulta consulta) {
        return new ConsultaDtoGraphQL(
                consulta.getId(),
                consulta.getIdMedico(),
                consulta.getIdPaciente(),
                consulta.getIdUsuarioCriacao(),
                toOffset(consulta.getDataCriacao()),
                consulta.getIdUsuarioAtualizacao(),
                toOffset(consulta.getDataAtualizacao()),
                toOffset(consulta.getDataConsulta()),
                consulta.getStatus() != null ? consulta.getStatus().name() : null
        );
    }

    private static OffsetDateTime toOffset(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atZone(ZONE).toOffsetDateTime();
    }
}
