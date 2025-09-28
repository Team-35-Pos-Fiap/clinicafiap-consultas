package br.com.clinicafiap.exceptions;

public class ConsultaNaoEncontradaException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ConsultaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}