package com.ALURA_CHALLENGE.FORO.Domain.Topico.validaciones;

import com.ALURA_CHALLENGE.FORO.Domain.Cursos.CursoRepository;
import com.ALURA_CHALLENGE.FORO.Domain.Topico.DatosCrearTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarCursoCreado implements ValidarTopicoRepository {

    @Autowired
    private CursoRepository repository;

    @Override
    public void validate(DatosCrearTopico topico) {
        var existe = repository.existsById(topico.cursoId());
        if(!existe){
            throw new ValidationException("ERROR.\n El curso solicitado no exite.");
        }
    }
}
