package com.grupo11.sistema_tele_pizza_backend.aplicacao.requests;

public class RegistrarClienteRequest {
    private String nome;
    private String cpf;
    private String celular;
    private String endereco;
    private String email;
    private String senha;

    public RegistrarClienteRequest(String nome, String cpf, String celular, String endereco, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
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