package com.grupo11.sistema_tele_pizza_backend.dominio.dados;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cliente;

import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> findById(Long id);
}
