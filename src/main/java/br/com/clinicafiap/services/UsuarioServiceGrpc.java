package br.com.clinicafiap.services;

import br.com.clinicafiap.entities.dto.UsuarioDto;
import br.com.clinicafiap.grpc.UsuarioGrpcClient;
import br.com.clinicafiap.grpc.usuario.UsuarioResponse;
import br.com.clinicafiap.grpc.usuario.ValidaUsuariosParaAgendamentoResponse;
import br.com.clinicafiap.mappers.UsuarioProtoMapper;
import br.com.clinicafiap.services.interfaces.IUsuarioService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioServiceGrpc implements IUsuarioService {

    private final UsuarioGrpcClient usuarioGrpcClient;

    public UsuarioServiceGrpc(UsuarioGrpcClient usuarioGrpcClient) {
        this.usuarioGrpcClient = usuarioGrpcClient;
    }

    @Override
    public UsuarioDto buscarPorId(UUID id) {
            UsuarioResponse usuarioResponse = usuarioGrpcClient.getUsuario(id);

            return UsuarioProtoMapper.toUsuarioDto(usuarioResponse);
    }

    @Override
    public ValidaUsuariosParaAgendamentoResponse validaUsuariosParaAgendamento(UUID idMedico, UUID idPaciente, UUID idUsuarioCriacao) {
        return usuarioGrpcClient.validaUsuariosParaAgendamento(
                idMedico,
                idPaciente,
                idUsuarioCriacao
        );
    }
}
