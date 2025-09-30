package br.com.clinicafiap.services.interfaces;

import br.com.clinicafiap.entities.db.ConsultaDb;
import br.com.clinicafiap.entities.record.NotificacaoRecord;

public interface INotificacaoService {
	void sendMessageNotificacao(ConsultaDb consulta);
}