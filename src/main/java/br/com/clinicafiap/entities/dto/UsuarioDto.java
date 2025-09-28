package br.com.clinicafiap.entities.dto;

import java.util.UUID;

public record UsuarioDto(UUID id, String nome, String perfil) {

}