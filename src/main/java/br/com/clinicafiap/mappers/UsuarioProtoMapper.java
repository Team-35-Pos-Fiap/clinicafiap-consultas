package br.com.clinicafiap.mappers;

import br.com.clinicafiap.entities.dto.UsuarioDto;
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

    public static UsuarioDto toUsuarioDto(UsuarioResponse usuarioResponse) {
        String perfilNome = toPerfilString(usuarioResponse.getPerfil());

        return new UsuarioDto(
                UUID.fromString(usuarioResponse.getId()),
                usuarioResponse.getNome(),
                perfilNome,
                usuarioResponse.getEmail()
        );
    }
}
