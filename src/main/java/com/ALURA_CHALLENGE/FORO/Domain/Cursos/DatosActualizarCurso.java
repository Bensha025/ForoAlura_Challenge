package com.ALURA_CHALLENGE.FORO.Domain.Cursos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull
        Long id,
        String nombre,
        Categoria categoria
) {
}
