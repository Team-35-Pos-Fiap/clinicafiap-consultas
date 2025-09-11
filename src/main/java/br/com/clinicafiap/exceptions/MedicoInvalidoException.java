package br.com.clinicafiap.exceptions;

public class MedicoInvalidoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MedicoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
