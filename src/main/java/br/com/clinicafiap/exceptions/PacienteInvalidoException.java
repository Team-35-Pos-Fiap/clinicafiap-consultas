package br.com.clinicafiap.exceptions;

public class PacienteInvalidoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PacienteInvalidoException(String mensagem) {
		super(mensagem);
	}
}
