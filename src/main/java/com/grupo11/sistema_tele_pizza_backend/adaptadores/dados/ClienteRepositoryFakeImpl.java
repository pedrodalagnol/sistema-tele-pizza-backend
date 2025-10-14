package com.grupo11.sistema_tele_pizza_backend.adaptadores.dados;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.ClienteRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ClienteRepositoryFakeImpl implements ClienteRepository {

    private final Map<Long, Cliente> clientes = new ConcurrentHashMap<>();

    public ClienteRepositoryFakeImpl() {
        // Pre-populate with a sample client for testing
        clientes.put(1L, new Cliente(1L, "Cliente Teste", "12345678901", "51999999999", "Rua Teste, 123", "cliente@teste.com", "senha123"));
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return Optional.ofNullable(clientes.get(id));
    }
}
