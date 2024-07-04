package com.ALURA_CHALLENGE.FORO.Domain.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        Status status,
        Long usuarioId,
        Long CursoId
) {
}
