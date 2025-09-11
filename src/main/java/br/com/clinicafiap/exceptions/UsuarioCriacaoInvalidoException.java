package br.com.clinicafiap.exceptions;

public class UsuarioCriacaoInvalidoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UsuarioCriacaoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
