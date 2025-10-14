package com.grupo11.sistema_tele_pizza_backend.aplicacao;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.CozinhaService;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PagarPedidoUC {

    private final PedidoRepository pedidoRepository;
    private final PagamentoService pagamentoService;
    private final CozinhaService cozinhaService;

    @Autowired
    public PagarPedidoUC(PedidoRepository pedidoRepository, PagamentoService pagamentoService, CozinhaService cozinhaService) {
        this.pedidoRepository = pedidoRepository;
        this.pagamentoService = pagamentoService;
        this.cozinhaService = cozinhaService;
    }

    public void run(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));

        if (pedido.getStatus() != Pedido.Status.APROVADO) {
            throw new IllegalStateException("O pedido não pode ser pago pois não está no estado APROVADO.");
        }

        boolean pagamentoEfetuado = pagamentoService.processarPagamento(pedido);

        if (pagamentoEfetuado) {
            // In a real application, you would create a new Pedido object
            pedido.setStatus(Pedido.Status.PAGO);
            // pedido.setDataHoraPagamento(LocalDateTime.now());
            pedidoRepository.save(pedido);
            cozinhaService.chegadaDePedido(pedido);
        } else {
            throw new RuntimeException("Pagamento não autorizado.");
        }
    }
}
