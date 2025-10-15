package com.grupo11.sistema_tele_pizza_backend.aplicacao.responses;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.PedidoDetalhadoResponse;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListarPedidosPorClienteUC {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public ListarPedidosPorClienteUC(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoDetalhadoResponse> run(Long clienteId, LocalDateTime inicio, LocalDateTime fim) {
        return pedidoRepository.findPedidosByCliente_IdAndStatusAndDataHoraPagamentoBetween(clienteId, Pedido.Status.ENTREGUE, inicio, fim).stream()
                .map(PedidoDetalhadoResponse::new)
                .collect(Collectors.toList());
    }
}