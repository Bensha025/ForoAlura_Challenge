package com.ALURA_CHALLENGE.FORO.Domain.Topico.validaciones;


import com.ALURA_CHALLENGE.FORO.Domain.Topico.DatosCrearTopico;
import com.ALURA_CHALLENGE.FORO.Domain.Usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarTopicoUsuario implements ValidarTopicoRepository {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(DatosCrearTopico topico) {
        var existe = repository.existsById(topico.usuarioId());
        if(!existe){
            throw new ValidationException("ERROR.\nEl usuario solicitado no existe");
        }
    }
}


