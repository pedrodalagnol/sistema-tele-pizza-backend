package com.grupo11.sistema_tele_pizza_backend.dominio.dados;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(value = "SELECT * FROM pedidos p WHERE p.cliente_id = :clienteIdParam AND p.data_hora_pagamento >= :dataHoraPagamentoParam", nativeQuery = true)
    List<Pedido> findPedidosByClienteIdAndPaymentDateTime(@Param("clienteIdParam") Long clienteId, @Param("dataHoraPagamentoParam") LocalDateTime dataHoraPagamento);
    Optional<Pedido> findById(Long id);
    List<Pedido> findPedidosByStatusAndDataHoraPagamentoBetween(Pedido.Status status, LocalDateTime inicio, LocalDateTime fim);
    List<Pedido> findPedidosByCliente_IdAndStatusAndDataHoraPagamentoBetween(@Param("cId") Long clienteId, @Param("status") Pedido.Status status, @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}