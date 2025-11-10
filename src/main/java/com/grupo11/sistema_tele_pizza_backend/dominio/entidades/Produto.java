package com.grupo11.sistema_tele_pizza_backend.dominio.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    @ManyToOne
    private Receita receita;
    private int preco;
    private boolean disponivel;

    public Produto(long id,String descricao, Receita receita, int preco) {
        if (!Produto.precoValido(preco))
            throw new IllegalArgumentException("Preco invalido: " + preco);
        if (descricao == null || descricao.isEmpty())
            throw new IllegalArgumentException("Descricao invalida");
        if (receita == null)
            throw new IllegalArgumentException("Receita invalida");
        this.id = id;
        this.descricao = descricao;
        this.receita = receita;
        this.preco = preco;
        this.disponivel = true;
    }

    public Produto() {

    }

    public long getId(){
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Receita getReceita() {
        return receita;
    }

    public int getPreco() {
        return preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setPreco(int preco) {
        if (!Produto.precoValido(preco))
            throw new IllegalArgumentException("Preco invalido: " + preco);
        this.preco = preco;
    }

    // Valida um preco (preco em centavos)
    public static boolean precoValido(int preco) {
        return preco > 0;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", descricao=" + descricao + ", receita=" + receita + ", preco=" + preco + "]";
    }
    
}
