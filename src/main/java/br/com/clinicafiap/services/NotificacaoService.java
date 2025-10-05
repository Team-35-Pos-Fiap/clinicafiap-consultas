package br.com.clinicafiap.services;


import br.com.clinicafiap.entities.domain.Consulta;
import br.com.clinicafiap.entities.dto.UsuarioDto;
import br.com.clinicafiap.entities.record.NotificacaoRecord;
import br.com.clinicafiap.services.interfaces.INotificacaoService;
import br.com.clinicafiap.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService implements INotificacaoService {

    private INotificacaoService notificacaoService;

    private final KafkaTemplate<String, NotificacaoRecord> kafkaTemplateNotificacao;


    public NotificacaoService(KafkaTemplate<String, NotificacaoRecord> kafkaTemplateNotificacao) {
        this.kafkaTemplateNotificacao = kafkaTemplateNotificacao;
    }

    @SuppressWarnings("null")
    public void sendMessageNotificacao(Consulta consulta, UsuarioDto usuario) {
        int partition = 1;

        NotificacaoRecord notificacao = new NotificacaoRecord(
                usuario.email(),
                "Consulta " + consulta.getStatus(),
                "Saudações, sr(a) " + usuario.nome() +
                        ", \n\n" +
                        "Sua consulta foi " + consulta.getStatus() +
                        " com sucesso para o dia " + consulta.getDataConsulta().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm")) + "." +
                        "\n\n" +
                        "Atenciosamente, \nClínica Fiap."
        );

        System.out.println("Sent message to partition: " + partition);
        System.out.println("Sending notificacao para: " + usuario.email());
        kafkaTemplateNotificacao.send("notificacao-email", partition, null, notificacao);
    }

}
