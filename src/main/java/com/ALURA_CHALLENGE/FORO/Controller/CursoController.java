package com.ALURA_CHALLENGE.FORO.Controller;

import com.ALURA_CHALLENGE.FORO.Domain.Cursos.*;
import com.ALURA_CHALLENGE.FORO.Domain.Respuesta.DatosRespuestaRespuesta;
import com.ALURA_CHALLENGE.FORO.Domain.Respuesta.Respuesta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Curso", description = "Controla los cursos dento del foro")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> crearCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
                                                          UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria());
        URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoCurso>> listarCursos(Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findByActivoTrue(paginacion).map(DatosListadoCurso::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> retornarDatosUsuario(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        var datoUsuario = new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria());
        return ResponseEntity.ok(datoUsuario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> actualizarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso){
        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarDatosCurso(datosActualizarCurso);
        return ResponseEntity.ok(new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> borrarCurso(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        curso.desactivarCurso();
        return ResponseEntity.noContent().build();
    }
}
