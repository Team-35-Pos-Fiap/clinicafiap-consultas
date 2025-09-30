package br.com.clinicafiap.mappers;

import br.com.clinicafiap.entities.dto.UsuarioDtoResponse;
import br.com.clinicafiap.grpc.usuario.TipoPerfil;
import br.com.clinicafiap.grpc.usuario.UsuarioResponse;

import java.util.UUID;

public class UsuarioProtoMapper {

    public static String toPerfilString(TipoPerfil tipoPerfil) {
        return switch (tipoPerfil) {
            case MEDICO -> "Médico";
            case PACIENTE -> "Paciente";
            case ENFERMEIRO -> "Enfermeiro";
            default -> "Indefinido";
        };
    }

    public static UsuarioDtoResponse toUsuarioDtoResponse(UsuarioResponse usuarioResponse) {
        String perfilNome = toPerfilString(usuarioResponse.getPerfil());

        return new UsuarioDtoResponse(
                UUID.fromString(usuarioResponse.getId()),
                usuarioResponse.getNome(),
                usuarioResponse.getEmail(),
                usuarioResponse.getAtivo(),
                perfilNome
        );
    }
}
