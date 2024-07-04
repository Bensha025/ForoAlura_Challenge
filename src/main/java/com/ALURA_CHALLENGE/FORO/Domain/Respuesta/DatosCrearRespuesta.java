package com.ALURA_CHALLENGE.FORO.Domain.Respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCrearRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        Long usuarioId,
        @NotNull
        long topicoId
) {
}
