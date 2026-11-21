package com.fullstack3.threads.dto;

import java.time.LocalDateTime;

public class CategoriaResponseDTO {

    private Long idCategoria;
    private Integer codigoCategoria;
    private String nombreCategoria;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaCierre;

    public CategoriaResponseDTO() {
    }

    public CategoriaResponseDTO(Long idCategoria, Integer codigoCategoria, String nombreCategoria,
                                LocalDateTime fechaCreacion, LocalDateTime fechaModificacion,
                                LocalDateTime fechaCierre) {
        this.idCategoria = idCategoria;
        this.codigoCategoria = codigoCategoria;
        this.nombreCategoria = nombreCategoria;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaCierre = fechaCierre;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(Integer codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
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