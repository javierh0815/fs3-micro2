package com.fullstack3.threads.model;

import java.util.List;

import jakarta.persistence.*;

@Entity 
@Table(name="categorias")
public class Categoria {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;


    @Column(name ="categoria", nullable = false, length = 100)
    private String nombre_categoria;


    public Categoria() {
    }

    public Categoria(Long idCategoria, String nombre_categoria) {
        this.idCategoria = idCategoria;
        this.nombre_categoria = nombre_categoria;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }


    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tema> temas;









}
