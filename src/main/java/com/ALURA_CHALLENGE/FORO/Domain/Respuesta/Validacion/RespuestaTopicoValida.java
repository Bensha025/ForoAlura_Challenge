package com.ALURA_CHALLENGE.FORO.Domain.Respuesta.Validacion;

import com.ALURA_CHALLENGE.FORO.Domain.Respuesta.DatosCrearRespuesta;
import com.ALURA_CHALLENGE.FORO.Domain.Topico.DatosRespuestaTopico;
import com.ALURA_CHALLENGE.FORO.Domain.Topico.Status;
import com.ALURA_CHALLENGE.FORO.Domain.Topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RespuestaTopicoValida implements ValidarRespuestaRepository {

    @Autowired
    private TopicoRepository repository;

    @Override
    public void validate(DatosCrearRespuesta respuesta) {
        var existe = repository.existsById(respuesta.topicoId());

        if (!existe){
            throw new ValidationException("ERROR.\n El topico solicitado no existe");
        }

        var topicoAbierto = repository.findById(respuesta.topicoId()).get().getStatus();

        if(topicoAbierto != Status.ABIERTO){
            throw new ValidationException("ERROR.\n El topico solicitado no esta abierto.");
        }
    }
}

