package com.ALURA_CHALLENGE.FORO.Domain.Topico;

import com.ALURA_CHALLENGE.FORO.Domain.Cursos.Categoria;
import com.ALURA_CHALLENGE.FORO.Domain.Respuesta.Respuesta;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        Status status,
        String usuario,
        String curso,
        Categoria categoria) {

    public DatosListadoTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getUsuario().getUsername(),
                topico.getCurso().getNombre(),
                topico.getCurso().getCategoria());
    }

}
