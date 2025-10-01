package br.com.clinicafiap.grpc.exceptions;

public class ServicoIndisponivelException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ServicoIndisponivelException(String mensagem) { super(mensagem); }
}
