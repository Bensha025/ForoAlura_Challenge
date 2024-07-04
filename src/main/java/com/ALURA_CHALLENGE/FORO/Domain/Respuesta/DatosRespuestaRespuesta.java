package com.ALURA_CHALLENGE.FORO.Domain.Respuesta;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        Boolean solucion,
        Long usuarioId,
        String usuario,
        Long topicoId,
        String topico
) {
}
