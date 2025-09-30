package br.com.clinicafiap.grpc.exceptions;

public class ErroInternoUsuariosException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ErroInternoUsuariosException(String mensagem) { super(mensagem); }
}
