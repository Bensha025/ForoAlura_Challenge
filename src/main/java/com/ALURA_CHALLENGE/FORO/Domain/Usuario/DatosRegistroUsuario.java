package com.ALURA_CHALLENGE.FORO.Domain.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        Long id,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotBlank
        @Email
        String email) {
}
