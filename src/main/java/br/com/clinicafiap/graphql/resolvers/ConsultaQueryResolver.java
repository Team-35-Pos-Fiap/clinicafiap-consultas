package br.com.clinicafiap.graphql.resolvers;

import br.com.clinicafiap.entities.domain.Consulta;
import br.com.clinicafiap.graphql.dto.ConsultaDtoGraphQL;
import br.com.clinicafiap.graphql.mappers.ConsultaGraphQLMapper;
import br.com.clinicafiap.mappers.ConsultaMapper;
import br.com.clinicafiap.repositories.interfaces.IConsultaRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class ConsultaQueryResolver {

    private IConsultaRepository consultaRepository;

    public ConsultaQueryResolver(IConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    @QueryMapping
    public List<ConsultaDtoGraphQL> consultaPorPaciente(@Argument UUID idPaciente) {
        List<Consulta> consultas = ConsultaMapper.toListConsultas(
                consultaRepository.buscarConsultasPorPaciente(idPaciente)
        );

        return consultas.stream().map(ConsultaGraphQLMapper::toConsultaDtoGraphQL).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    @QueryMapping
    public List<ConsultaDtoGraphQL> consultaPorMedico(@Argument UUID idMedico) {
        List<Consulta> consultas = ConsultaMapper.toListConsultas(
                consultaRepository.buscarConsultasPorMedico(idMedico)
        );
        return consultas.stream().map(ConsultaGraphQLMapper::toConsultaDtoGraphQL).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    @QueryMapping
    public List<ConsultaDtoGraphQL> consultaPorPeriodo(@Argument LocalDateTime dataInicio,
                                                       @Argument LocalDateTime dataFim) {
        List<Consulta> consultas = ConsultaMapper.toListConsultas(
                consultaRepository.buscarConsultasPorPeriodo(dataInicio, dataFim)
        );
        return consultas.stream()
                .filter(c -> c.getDataConsulta() != null
                        && !c.getDataConsulta().isBefore(dataInicio)
                        && !c.getDataConsulta().isAfter(dataFim))
                .sorted(Comparator.comparing(Consulta::getDataConsulta,
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .map(ConsultaGraphQLMapper::toConsultaDtoGraphQL)
                .collect(Collectors.toList());
    }
}
