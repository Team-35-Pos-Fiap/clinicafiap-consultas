package br.com.clinicafiap.grpc;


import br.com.clinicafiap.grpc.usuario.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UsuarioGrpcClient {

    @GrpcClient("usuario")
    private UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioServiceBlockingStub;

    public UsuarioResponse getUsuario(UUID id) {
        return usuarioServiceBlockingStub.getUsuario(GetUsuarioRequest.newBuilder()
                .setId(id.toString()).build());
    }

    public ValidaUsuariosParaAgendamentoResponse validaUsuariosParaAgendamento(UUID idMedico, UUID idPaciente, UUID idUsuarioCriacao) {
        return usuarioServiceBlockingStub.validaUsuariosParaAgendamento(
                ValidaUsuariosParaAgendamentoRequest.newBuilder()
                        .setIdMedico(idMedico.toString())
                        .setIdPaciente(idPaciente.toString())
                        .setIdUsuarioCriacao(idUsuarioCriacao.toString())
                        .build()
        );
    }
}
