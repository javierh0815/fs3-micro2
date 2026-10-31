package com.fullstack3.threads.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "temas")
public class Tema {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tema")
    private Long idTema;
    
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "nombre_usuario", nullable = false, length = 100)
    private String nombreUsuario;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_cierre", nullable = true)
    private LocalDateTime fechaCierre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> comentarios;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
        
    public Tema() {
    }

    public Tema(Long idTema, String titulo, String nombreUsuario, LocalDateTime fechaCreacion, LocalDateTime fechaCierre, Categoria categoria) {
        this.idTema = idTema;
        this.titulo = titulo;
        this.nombreUsuario = nombreUsuario;
        this.fechaCreacion = fechaCreacion;
        this.fechaCierre = fechaCierre;
        this.categoria = categoria;
    }

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}