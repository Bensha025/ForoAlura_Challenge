package com.ALURA_CHALLENGE.FORO.Domain.Respuesta.Validacion;


import com.ALURA_CHALLENGE.FORO.Domain.Respuesta.DatosCrearRespuesta;
import com.ALURA_CHALLENGE.FORO.Domain.Usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RespuestaUsuarioValida implements ValidarRespuestaRepository{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(DatosCrearRespuesta respuesta) {
        var existe = repository.existsById(respuesta.usuarioId());

        if(!existe){
            throw new ValidationException("ERROR.\nEl usuario solicitado no existe");
        }

        var usuarioHabilitado = repository.findById(respuesta.usuarioId()).get().isEnabled();

        if(!usuarioHabilitado){
            throw new ValidationException("ERROR.\nEl usuario solicitado no esta habilitado");
        }
    }
}
