package br.com.clinicafiap.exceptions;

public class DataConsultaInvalidaException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataConsultaInvalidaException(String mensagem) {
		super(mensagem);
	}
}
