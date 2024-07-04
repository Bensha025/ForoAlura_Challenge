package com.ALURA_CHALLENGE.FORO.Domain.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<DatosListadoUsuario> findByHabilitadoTrue(Pageable paginacion);
    UserDetails findByUsername(String username);
    UserDetails findByEmail(String email);
}
