package com.grupo11.sistema_tele_pizza_backend.dominio.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cliente;

import java.util.Optional;

public interface AutenticacaoService {
    Optional<Cliente> autenticar(String email, String senha);
}