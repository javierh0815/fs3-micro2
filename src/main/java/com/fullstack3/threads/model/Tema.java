package com.fullstack3.threads.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "temas")
public class Tema {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tema")
    private Long idTema;

    @Generated(event = EventType.INSERT)
    @Column(name = "cod_tema", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer codigoTema;
    
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

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
    @JoinColumn(name = "id_categoria", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categoria categoria;

    @JsonIgnore 
    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> comentarios;

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
        
    public Tema() {
    }

    public Tema(Long idTema, Integer codigoTema, String titulo, Integer codUsuario, String nombreUsuario, 
                LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, LocalDateTime fechaCierre, Categoria categoria) {
        this.idTema = idTema;
        this.codigoTema = codigoTema;
        this.titulo = titulo;
        this.codUsuario = codUsuario;
        this.nombreUsuario = nombreUsuario;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaCierre = fechaCierre;
        this.categoria = categoria;
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