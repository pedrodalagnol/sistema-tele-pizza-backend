package com.grupo11.sistema_tele_pizza_backend.dominio.entidades;

public class Cliente {
    private Long id;
    private String cpf;
    private String nome;
    private String celular;
    private String endereco;
    private String email;
    private String senha;

    public Cliente(String cpf, String nome, String celular, String endereco, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.celular = celular;
        this.endereco = endereco;
        this.email = email;
    }

    public Cliente(Long id, String nome, String cpf, String celular, String endereco, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCelular() {
        return celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
