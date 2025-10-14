package com.grupo11.sistema_tele_pizza_backend.aplicacao;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.StatusPedidoResponse;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultarStatusPedidoUC {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public ConsultarStatusPedidoUC(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public StatusPedidoResponse run(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));

        return new StatusPedidoResponse(pedido.getId(), pedido.getStatus().name());
    }
}
