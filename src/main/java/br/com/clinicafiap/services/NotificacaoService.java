package br.com.clinicafiap.services;

import br.com.clinicafiap.entities.db.ConsultaDb;
import br.com.clinicafiap.entities.dto.UsuarioDtoResponse;
import br.com.clinicafiap.entities.record.NotificacaoRecord;
import br.com.clinicafiap.services.interfaces.INotificacaoService;
import br.com.clinicafiap.services.interfaces.IUsuarioService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService implements INotificacaoService {

    private INotificacaoService notificacaoService;
    private IUsuarioService usuarioService;
    private final KafkaTemplate<String, NotificacaoRecord> kafkaTemplateNotificacao;


    public NotificacaoService(KafkaTemplate<String, NotificacaoRecord> kafkaTemplateNotificacao) {
        this.kafkaTemplateNotificacao = kafkaTemplateNotificacao;
    }

    @SuppressWarnings("null")
    public void sendMessageNotificacao(ConsultaDb consulta) {
        int partition = 1;

        UsuarioDtoResponse usuario = usuarioService.buscarPorId(consulta.getIdPaciente());

        NotificacaoRecord notificacao = new NotificacaoRecord(
                usuario.email(),
                "Consulta Agendada",
                "Saudações sr(a) " + usuario.nome() +", sua consulta foi agendada com sucesso para o dia " + consulta.getDataConsulta()
        );

        System.out.println("Sent message to partition: " + partition);
        System.out.println("Sending notificacao para: " + usuario.email());
        kafkaTemplateNotificacao.send("notificacao-email", partition, null, notificacao);
    }

}
