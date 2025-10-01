package br.com.clinicafiap.grpc.exceptions;

public class ParametroInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ParametroInvalidoException(String mensagem) { super(mensagem); }
}
