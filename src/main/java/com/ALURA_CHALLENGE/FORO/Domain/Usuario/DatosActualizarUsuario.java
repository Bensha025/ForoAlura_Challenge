package com.ALURA_CHALLENGE.FORO.Domain.Usuario;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotNull
        Long id,
        String username,
        String password,
        String nombre,
        String apellido,
        String email) {
}
