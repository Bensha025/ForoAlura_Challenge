package com.ALURA_CHALLENGE.FORO.Domain.Topico.validaciones;

import com.ALURA_CHALLENGE.FORO.Domain.Topico.DatosCrearTopico;

public interface ValidarTopicoRepository {

    public void validate(DatosCrearTopico topico);
}
