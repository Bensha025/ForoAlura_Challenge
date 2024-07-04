package com.ALURA_CHALLENGE.FORO.Domain.Usuario.validaciones;

import com.ALURA_CHALLENGE.FORO.Domain.Usuario.DatosRegistroUsuario;
import com.ALURA_CHALLENGE.FORO.Domain.Usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDuplicado implements ValidarUsuarioRepository {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(DatosRegistroUsuario usuario) {
        var usuarioDuplicado = repository.findByUsername(usuario.username());
        if(usuarioDuplicado != null){
            throw new ValidationException("ERROR.\nEl usuario solicitado ya existe.");
        }

        var emailDuplicado = repository.findByEmail(usuario.email());
        if(emailDuplicado != null){
            throw new ValidationException("ERROR.\nEl email solicitado ya existe.");
        }
    }
}

