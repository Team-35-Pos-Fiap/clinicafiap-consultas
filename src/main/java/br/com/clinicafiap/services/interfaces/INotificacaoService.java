package br.com.clinicafiap.services.interfaces;

import br.com.clinicafiap.entities.db.ConsultaDb;
import br.com.clinicafiap.entities.domain.Consulta;
import br.com.clinicafiap.entities.dto.UsuarioDto;

public interface INotificacaoService {
	void sendMessageNotificacao(Consulta consulta, UsuarioDto usuario);
}