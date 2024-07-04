package com.ALURA_CHALLENGE.FORO.Domain.Respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotNull
        Long id,
        String mensaje) {
}
