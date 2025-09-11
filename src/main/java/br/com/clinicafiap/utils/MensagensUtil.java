package br.com.clinicafiap.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MensagensUtil {
	private static MessageSource messageSource;
	
	private static Locale locale = Locale.of("pt", "BR");
	
	public static final String ERRO_PARAMETRO_INVALIDO = "exception.erro_parametro_invalido";
	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		MensagensUtil.messageSource = messageSource;
	}
	
	public static String recuperarMensagem(String mensagem, Object ... parametros) {
		return messageSource.getMessage(mensagem, parametros, locale);
	}
}