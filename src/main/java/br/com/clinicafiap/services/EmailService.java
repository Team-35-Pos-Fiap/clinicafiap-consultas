package br.com.clinicafiap.services;

import br.com.clinicafiap.entities.record.EmailRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final KafkaTemplate<String, EmailRecord> kafkaTemplateEmail;

    public EmailService(KafkaTemplate<String, EmailRecord> kafkaTemplateEmail) {
        this.kafkaTemplateEmail = kafkaTemplateEmail;
    }

    @SuppressWarnings("null")
    public void sendMessageNotificacao(EmailRecord email) {
        int partition = 1;
        System.out.println("Sent message to partition: " + partition);
        System.out.println("Sending notificacao para: " + email.destinatario());
        kafkaTemplateEmail.send("notificacao-email", partition, null, email);
    }

}
