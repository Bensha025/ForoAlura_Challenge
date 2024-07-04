package com.ALURA_CHALLENGE.FORO.Domain.Respuesta;

import com.ALURA_CHALLENGE.FORO.Domain.Topico.Status;
import com.ALURA_CHALLENGE.FORO.Domain.Topico.Topico;
import com.ALURA_CHALLENGE.FORO.Domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuesta")
@Entity(name = "Respuesta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaDeCreacion;
    private Boolean solucion;
    private Boolean eliminado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Topico topico;

    public Respuesta(DatosCrearRespuesta datosCrearRespuesta, Usuario usuario, Topico topico) {
        this.mensaje = datosCrearRespuesta.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.solucion = true;
        this.eliminado = false;
        this.usuario = usuario;
        this.topico = topico;
        if(this.solucion == true){
            topico.setStatus(Status.CERRADO);
        }
    }

    public void actualizarDatos(DatosActualizarRespuesta datosActualizarRespuesta) {
        if (datosActualizarRespuesta.mensaje() != null){
            this.mensaje = datosActualizarRespuesta.mensaje();
        }
    }

    public void eliminarRespuesta() {
        this.eliminado = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Boolean getSolucion() {
        return solucion;
    }

    public void setSolucion(Boolean solucion) {
        this.solucion = solucion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

}
