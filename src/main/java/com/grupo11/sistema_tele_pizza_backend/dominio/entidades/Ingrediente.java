package com.grupo11.sistema_tele_pizza_backend.dominio.entidades;

public class Ingrediente {
    private long id;
    private String descricao;

    public Ingrediente(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public long getId() { return id; }
    public String getDescricao() { return descricao; }
}
