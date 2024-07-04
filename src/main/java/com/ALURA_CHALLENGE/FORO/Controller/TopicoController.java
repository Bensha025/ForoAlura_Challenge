package com.ALURA_CHALLENGE.FORO.Controller;

import com.ALURA_CHALLENGE.FORO.Domain.Cursos.Curso;
import com.ALURA_CHALLENGE.FORO.Domain.Cursos.CursoRepository;
import com.ALURA_CHALLENGE.FORO.Domain.Respuesta.Validacion.ValidarRespuestaRepository;
import com.ALURA_CHALLENGE.FORO.Domain.Topico.*;
import com.ALURA_CHALLENGE.FORO.Domain.Topico.validaciones.ValidarTopicoRepository;
import com.ALURA_CHALLENGE.FORO.Domain.Usuario.DatosRespuestaUsuario;
import com.ALURA_CHALLENGE.FORO.Domain.Usuario.Usuario;
import com.ALURA_CHALLENGE.FORO.Domain.Usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Topico", description = "Crea las peguntas sobre un tema de un curso.")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidarTopicoRepository> validacion;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@RequestBody @Valid DatosCrearTopico datosCrearTopico,
                                                            UriComponentsBuilder uriComponentsBuilder){
        validacion.forEach(v -> v.validate(datosCrearTopico));
        Usuario usuario = usuarioRepository.findById(datosCrearTopico.usuarioId()).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(datosCrearTopico.cursoId()).orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
        Topico topico = topicoRepository.save(new Topico(datosCrearTopico, usuario, curso));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getUsuario().getId(),
                topico.getCurso().getId());
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(Pageable paginacion){
        //return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatosUsuario(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datoUsuario = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getUsuario().getId(),
                topico.getCurso().getId());
        return ResponseEntity.ok(datoUsuario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualiazarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Curso curso = null;
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        if (datosActualizarTopico.cursoId() != null){
            curso = cursoRepository.findById(datosActualizarTopico.cursoId()).get();
        }
        topico.actualizarDatos(datosActualizarTopico, curso);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getUsuario().getId(),
                topico.getCurso().getId()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
