package com.ALURA_CHALLENGE.FORO.Domain.Usuario;

public record DatosListadoUsuario(
        Long id,
        String username,
        String Nombre,
        String apellido,
        String email) {

    public DatosListadoUsuario (Usuario usuario){
        this(usuario.getId(),
                usuario.getUsername(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail());
    }
}
