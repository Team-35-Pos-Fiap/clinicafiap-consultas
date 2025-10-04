package br.com.clinicafiap.security;

import br.com.clinicafiap.repositories.interfaces.IConsultaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConsultaSecurity {

    private final IConsultaRepository consultaRepository;

    public ConsultaSecurity(IConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public boolean isPacienteDaConsulta(UUID idConsulta, UUID idPaciente) {
        var consulta = consultaRepository.buscarPorId(idConsulta);

        return consulta.getIdPaciente().equals(idPaciente);
    }
}
