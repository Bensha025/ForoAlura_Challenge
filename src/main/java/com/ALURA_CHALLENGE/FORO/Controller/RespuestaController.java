package com.ALURA_CHALLENGE.FORO.Controller;

import com.ALURA_CHALLENGE.FORO.Domain.Respuesta.*;
import com.ALURA_CHALLENGE.FORO.Domain.Respuesta.Validacion.ValidarRespuestaRepository;
import com.ALURA_CHALLENGE.FORO.Domain.Topico.*;
import com.ALURA_CHALLENGE.FORO.Domain.Usuario.Usuario;
import com.ALURA_CHALLENGE.FORO.Domain.Usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/respuesta")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respuesta", description = "Responde a las preguntas de los topicos.")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    List<ValidarRespuestaRepository> validacion;

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> crearRespuesta(@RequestBody @Valid DatosCrearRespuesta datosCrearRespuesta,
                                                                  UriComponentsBuilder uriComponentsBuilder){
        validacion.forEach(v -> v.validate(datosCrearRespuesta));
        Usuario usuario = usuarioRepository.findById(datosCrearRespuesta.usuarioId()).get();
        Topico topico = topicoRepository.findById(datosCrearRespuesta.topicoId()).get();
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosCrearRespuesta, usuario, topico));
        DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaDeCreacion(),
                respuesta.getSolucion(),
                respuesta.getUsuario().getId(),
                respuesta.getUsuario().getNombre(),
                respuesta.getTopico().getId(),
                respuesta.getTopico().getTitulo());
        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuestas(Pageable paginacion){
        return ResponseEntity.ok(respuestaRepository.findByEliminadoFalse(paginacion).map(DatosListadoRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaRespuesta> retornarDatosUsuario(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        var datoUsuario = new DatosRespuestaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaDeCreacion(),
                respuesta.getSolucion(),
                respuesta.getUsuario().getId(),
                respuesta.getUsuario().getNombre(),
                respuesta.getTopico().getId(),
                respuesta.getTopico().getTitulo());
        return ResponseEntity.ok(datoUsuario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaRespuesta> editaRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarDatos(datosActualizarRespuesta);
        return ResponseEntity.ok(new DatosRespuestaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaDeCreacion(),
                respuesta.getSolucion(),
                respuesta.getUsuario().getId(),
                respuesta.getUsuario().getNombre(),
                respuesta.getTopico().getId(),
                respuesta.getTopico().getTitulo()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaRespuesta> eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.eliminarRespuesta();
        return ResponseEntity.noContent().build();
    }
}
