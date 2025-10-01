package br.com.clinicafiap.grpc.exceptions;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public class GrpcExceptionTranslator {

    private GrpcExceptionTranslator() {}

    public static RuntimeException traduzirException(StatusRuntimeException e) {
        Status status = e.getStatus();
        String descricao = status.getDescription();
        String mensagem = (descricao != null && !descricao.isBlank()) ? descricao : mensagemDefault(status.getCode());

        return switch (status.getCode()) {
            case NOT_FOUND -> new UsuarioNaoEncontradoException(mensagem);
            case INVALID_ARGUMENT -> new ParametroInvalidoException(mensagem);
            case UNAVAILABLE, DEADLINE_EXCEEDED -> new ServicoIndisponivelException(mensagem);
            case INTERNAL -> new ErroInternoUsuariosException(mensagem);
            default -> e;
        };
    }

    private static String mensagemDefault(Status.Code codigo) {
        return switch (codigo) {
            case NOT_FOUND -> "Usuário não encontrado";
            case INVALID_ARGUMENT -> "Parâmetro inválido";
            case INTERNAL -> "Ocorreu um erro interno";
            default -> "Erro ao chamar serviço de usuários";
        };
    }
}
