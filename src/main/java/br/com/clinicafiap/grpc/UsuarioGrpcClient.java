package br.com.clinicafiap.grpc;


import br.com.clinicafiap.grpc.exceptions.GrpcExceptionTranslator;
import br.com.clinicafiap.grpc.usuario.*;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UsuarioGrpcClient {

    @GrpcClient("usuario")
    private UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioServiceBlockingStub;

    public UsuarioResponse getUsuario(UUID id) {
        try {
            return usuarioServiceBlockingStub.getUsuario(GetUsuarioRequest.newBuilder()
                    .setId(id.toString()).build());
        } catch (StatusRuntimeException e) {
            throw GrpcExceptionTranslator.traduzirException(e);
        }
    }

    public ValidaUsuariosParaAgendamentoResponse validaUsuariosParaAgendamento(UUID idMedico, UUID idPaciente, UUID idUsuarioCriacao) {
        try {
            return usuarioServiceBlockingStub.validaUsuariosParaAgendamento(
                    ValidaUsuariosParaAgendamentoRequest.newBuilder()
                            .setIdMedico(idMedico.toString())
                            .setIdPaciente(idPaciente.toString())
                            .setIdUsuarioCriacao(idUsuarioCriacao.toString())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            throw GrpcExceptionTranslator.traduzirException(e);
        }
    }
}
