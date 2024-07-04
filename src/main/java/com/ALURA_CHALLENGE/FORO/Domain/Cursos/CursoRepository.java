package com.ALURA_CHALLENGE.FORO.Domain.Cursos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findByActivoTrue(Pageable paginacion);

    record DatosActualizarCurso() {
    }
}
