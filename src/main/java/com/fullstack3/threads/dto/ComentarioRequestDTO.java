package com.fullstack3.threads.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ComentarioRequestDTO {

    @NotBlank(message = "El contenido del comentario es obligatorio")
    @Size(min = 1, max = 500, message = "El comentario no puede superar los 500 caracteres")
    private String contenido;

    @NotNull(message = "El código de usuario es obligatorio")
    private Integer codUsuario;

    @NotNull(message = "El ID del tema es obligatorio")
    private Long idTema;

    public ComentarioRequestDTO() {
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

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }
}