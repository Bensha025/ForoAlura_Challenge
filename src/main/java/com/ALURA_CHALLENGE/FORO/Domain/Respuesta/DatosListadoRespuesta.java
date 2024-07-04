package com.ALURA_CHALLENGE.FORO.Domain.Respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha,
        Boolean solucion,
        Long usuarioId,
        String username,
        Long topicoId,
        String topico) {

    public DatosListadoRespuesta(Respuesta respuesta){
        this(respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaDeCreacion(),
                respuesta.getSolucion(),
                respuesta.getUsuario().getId(),
                respuesta.getUsuario().getNombre(),
                respuesta.getTopico().getId(),
                respuesta.getTopico().getTitulo());
    }
}
