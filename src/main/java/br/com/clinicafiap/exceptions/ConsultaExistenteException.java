package br.com.clinicafiap.exceptions;

public class ConsultaExistenteException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ConsultaExistenteException(String mensagem) {
		super(mensagem);
	}
}