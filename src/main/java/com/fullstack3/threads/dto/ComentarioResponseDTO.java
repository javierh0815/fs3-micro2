package com.fullstack3.threads.dto;

import java.time.LocalDateTime;

public class ComentarioResponseDTO {

    private Long idComentario;
    private Integer codigoComentario;
    private String contenido;
    private Integer codUsuario;
    private String nombreUsuario;
    private Long idTema;
    private String tituloTema;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaCierre;

    public ComentarioResponseDTO() {
    }

    public ComentarioResponseDTO(Long idComentario, Integer codigoComentario, String contenido,
                                 Integer codUsuario, String nombreUsuario, Long idTema,
                                 String tituloTema, LocalDateTime fechaCreacion,
                                 LocalDateTime fechaModificacion, LocalDateTime fechaCierre) {
        this.idComentario = idComentario;
        this.codigoComentario = codigoComentario;
        this.contenido = contenido;
        this.codUsuario = codUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idTema = idTema;
        this.tituloTema = tituloTema;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaCierre = fechaCierre;
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

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    public String getTituloTema() {
        return tituloTema;
    }

    public void setTituloTema(String tituloTema) {
        this.tituloTema = tituloTema;
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
}