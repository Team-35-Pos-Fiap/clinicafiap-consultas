package br.com.clinicafiap.grpc.exceptions;

import br.com.clinicafiap.entities.dto.ErroUsuarioDto;

import java.util.List;

public class ValidacaoUsuariosException extends RuntimeException {
    private final List<ErroUsuarioDto> erros;

    public ValidacaoUsuariosException(List<ErroUsuarioDto> erros) {
        super("Erros na validação de usuários");
        this.erros = erros;
    }

    public List<ErroUsuarioDto> getErros() {return erros;}
}
