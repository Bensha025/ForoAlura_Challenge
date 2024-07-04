package com.ALURA_CHALLENGE.FORO.Domain.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaUsuario(
        Long id,
        String username,
        String nombre,
        String apellido,
        String email) {
}
