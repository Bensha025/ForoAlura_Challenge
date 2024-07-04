package com.ALURA_CHALLENGE.FORO.Domain.Topico.validaciones;

import com.ALURA_CHALLENGE.FORO.Domain.Topico.DatosCrearTopico;
import com.ALURA_CHALLENGE.FORO.Domain.Topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoDuplicado implements ValidarTopicoRepository {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validate(DatosCrearTopico topico) {
        var duplicado = topicoRepository.existsByTituloAndMensaje(topico.titulo(), topico.mensaje());
        if(duplicado){
            throw new ValidationException("ERROR.\nEl Topico solicitado ya fue creado.");

        }
    }
}


