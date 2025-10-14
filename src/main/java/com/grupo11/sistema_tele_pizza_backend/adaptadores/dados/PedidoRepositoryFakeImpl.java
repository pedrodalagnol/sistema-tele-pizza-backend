package com.grupo11.sistema_tele_pizza_backend.adaptadores.dados;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PedidoRepositoryFakeImpl implements PedidoRepository {

    private final Map<Long, Pedido> pedidos = new ConcurrentHashMap<>();
    private long nextId = 1;

    @Override
    public List<Pedido> findPedidosByClienteAndData(Long clienteId, LocalDateTime data) {
        return pedidos.values().stream()
                .filter(p -> p.getCliente().getId().equals(clienteId) && p.getDataHoraPagamento().isAfter(data))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return Optional.ofNullable(pedidos.get(id));
    }

    @Override
    public Pedido save(Pedido pedido) {
        if (pedido.getId() == 0) {
            long newId = nextId++;
            // In a real scenario, you would not create a new Pedido object here, but update the existing one with the new ID.
            // For this fake implementation, we'll assume the Pedido object is mutable or we create a new one.
        }
        pedidos.put(pedido.getId(), pedido);
        return pedido;
    }
}
