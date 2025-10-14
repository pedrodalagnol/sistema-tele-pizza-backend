package com.grupo11.sistema_tele_pizza_backend.dominio.dados;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    List<Pedido> findPedidosByClienteAndData(Long clienteId, LocalDateTime data);
    Optional<Pedido> findById(Long id);
    Pedido save(Pedido pedido);
}
