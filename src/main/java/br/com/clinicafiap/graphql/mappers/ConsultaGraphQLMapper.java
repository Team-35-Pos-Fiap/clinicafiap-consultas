package br.com.clinicafiap.graphql.mappers;

import br.com.clinicafiap.entities.domain.Consulta;
import br.com.clinicafiap.graphql.dto.ConsultaDtoGraphQL;

public class ConsultaGraphQLMapper {

    public static ConsultaDtoGraphQL toConsultaDtoGraphQL(Consulta consulta) {
        return new ConsultaDtoGraphQL(
                consulta.getId(),
                consulta.getIdMedico(),
                consulta.getIdPaciente(),
                consulta.getIdUsuarioCriacao(),
                consulta.getDataCriacao(),
                consulta.getIdUsuarioAtualizacao(),
                consulta.getDataAtualizacao(),
                consulta.getDataConsulta(),
                consulta.getStatus() != null ? consulta.getStatus().name() : null
        );
    }
}
