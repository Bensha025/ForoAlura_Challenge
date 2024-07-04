package com.ALURA_CHALLENGE.FORO.Domain.Cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(
        @NotBlank
        String name,
        @NotNull
        Categoria categoria
) {
}
