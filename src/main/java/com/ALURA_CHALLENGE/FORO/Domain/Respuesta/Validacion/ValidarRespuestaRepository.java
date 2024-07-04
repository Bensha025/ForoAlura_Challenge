package com.ALURA_CHALLENGE.FORO.Domain.Respuesta.Validacion;

import com.ALURA_CHALLENGE.FORO.Domain.Respuesta.DatosCrearRespuesta;

public interface ValidarRespuestaRepository {
    public void validate(DatosCrearRespuesta respuesta);
}
