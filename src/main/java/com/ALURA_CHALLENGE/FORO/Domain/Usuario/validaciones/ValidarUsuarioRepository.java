package com.ALURA_CHALLENGE.FORO.Domain.Usuario.validaciones;

import com.ALURA_CHALLENGE.FORO.Domain.Usuario.DatosRegistroUsuario;

public interface ValidarUsuarioRepository {
    public void validate(DatosRegistroUsuario usuario);
}
