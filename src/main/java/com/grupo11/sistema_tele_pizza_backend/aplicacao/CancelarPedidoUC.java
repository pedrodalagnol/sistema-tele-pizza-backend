package com.grupo11.sistema_tele_pizza_backend.aplicacao;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancelarPedidoUC {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public CancelarPedidoUC(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void run(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));

        if (pedido.getStatus() != Pedido.Status.APROVADO) {
            throw new IllegalStateException("O pedido não pode ser cancelado pois não está no estado APROVADO.");
        }

        pedido.setStatus(Pedido.Status.CANCELADO);
        pedidoRepository.save(pedido);
    }
}
