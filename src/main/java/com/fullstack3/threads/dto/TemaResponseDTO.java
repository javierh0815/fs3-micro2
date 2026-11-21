package com.fullstack3.threads.dto;

import java.time.LocalDateTime;

public class TemaResponseDTO {

    private Long idTema;
    private Integer codigoTema;
    private String titulo;
    private Integer codUsuario;
    private String nombreUsuario;
    private Long idCategoria;
    private String nombreCategoria;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaCierre;

    public TemaResponseDTO() {
    }

    public TemaResponseDTO(Long idTema, Integer codigoTema, String titulo, Integer codUsuario,
                           String nombreUsuario, Long idCategoria, String nombreCategoria,
                           LocalDateTime fechaCreacion, LocalDateTime fechaModificacion,
                           LocalDateTime fechaCierre) {
        this.idTema = idTema;
        this.codigoTema = codigoTema;
        this.titulo = titulo;
        this.codUsuario = codUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaCierre = fechaCierre;
    }

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    public Integer getCodigoTema() {
        return codigoTema;
    }

    public void setCodigoTema(Integer codigoTema) {
        this.codigoTema = codigoTema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
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