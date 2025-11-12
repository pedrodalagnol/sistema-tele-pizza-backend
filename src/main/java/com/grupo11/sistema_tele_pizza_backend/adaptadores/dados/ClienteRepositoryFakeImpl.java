package com.grupo11.sistema_tele_pizza_backend.adaptadores.dados;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.ClienteRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClienteRepositoryFakeImpl implements ClienteRepository {

    private final Map<Long, Cliente> clientes = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public ClienteRepositoryFakeImpl() {
        // Pre-populate with a sample client for testing
        Cliente clienteTeste = new Cliente(nextId.getAndIncrement(), "Cliente Teste", "12345678901", "51999999999", "Rua Teste, 123", "cliente@teste.com", "senha123");
        clientes.put(clienteTeste.getId(), clienteTeste);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return Optional.ofNullable(clientes.get(id));
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return clientes.values().stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public Optional<Cliente> findByUsername(String username) {
        return findByEmail(username);
    }

    @Override
    public Cliente save(Cliente cliente) {
        if (cliente.getId() == null || cliente.getId() == 0) {
            long newId = nextId.getAndIncrement();
            cliente = new Cliente(newId, cliente.getNome(), cliente.getCpf(), cliente.getCelular(), cliente.getEndereco(), cliente.getEmail(), cliente.getSenha());
        }
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }
}
