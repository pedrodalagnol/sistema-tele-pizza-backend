package com.grupo11.sistema_tele_pizza_backend.aplicacao.requests;

public class AutenticarClienteRequest {
    private String email;
    private String senha;

    public AutenticarClienteRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}