package br.com.clinicafiap.entities.record;

public record EmailRecord(Long id, String destinatario, String assunto, String mensagem) {
    
}
