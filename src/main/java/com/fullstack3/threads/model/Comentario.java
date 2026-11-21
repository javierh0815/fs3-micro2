package com.fullstack3.threads.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long idComentario;

    @Generated(event = EventType.INSERT)
    @Column(name = "cod_comentario", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer codigoComentario;

    @Column(name = "contenido", nullable = false, length = 500)
    private String contenido;

    @Column(name = "cod_usuario", nullable = false)
    private Integer codUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 100)
    private String nombreUsuario;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion", nullable = true)
    private LocalDateTime fechaModificacion;

    @Column(name = "fecha_cierre", nullable = true)
    private LocalDateTime fechaCierre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tema tema;

    @PrePersist
    public void prePersist() {
        LocalDateTime ahora = LocalDateTime.now();
        this.fechaCreacion = ahora;
        this.fechaModificacion = ahora; 
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = LocalDateTime.now();
    }

    public Comentario() {
    }

    public Comentario(Long idComentario, Integer codigoComentario, String contenido, Integer codUsuario, 
                      String nombreUsuario, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, 
                      LocalDateTime fechaCierre, Tema tema) {
        this.idComentario = idComentario;
        this.codigoComentario = codigoComentario;
        this.contenido = contenido;
        this.codUsuario = codUsuario;
        this.nombreUsuario = nombreUsuario;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaCierre = fechaCierre;
        this.tema = tema;
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public Integer getCodigoComentario() {
        return codigoComentario;
    }

    public void setCodigoComentario(Integer codigoComentario) {
        this.codigoComentario = codigoComentario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
}