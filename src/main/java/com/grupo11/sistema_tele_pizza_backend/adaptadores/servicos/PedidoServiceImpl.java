package com.grupo11.sistema_tele_pizza_backend.adaptadores.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.DescontoService;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.EstoqueService;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.ImpostoService;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final EstoqueService estoqueService;
    private final DescontoService descontoService;
    private final ImpostoService impostoService;
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServiceImpl(EstoqueService estoqueService, DescontoService descontoService, ImpostoService impostoService, PedidoRepository pedidoRepository) {
        this.estoqueService = estoqueService;
        this.descontoService = descontoService;
        this.impostoService = impostoService;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido aprovarPedido(Pedido pedido) {
        // 1. Verify stock
        if (!estoqueService.verificaDisponibilidade(pedido.getItens())) {
            throw new IllegalStateException("Itens do pedido indisponíveis no estoque.");
        }

        // 2. Calculate total value
        double valorTotal = pedido.getItens().stream()
                .mapToDouble(item -> item.getItem().getPreco() * item.getQuantidade())
                .sum();

        // Create a temporary Pedido with the value to calculate discount and tax
        Pedido tempPedido = new Pedido(pedido.getId(), pedido.getCliente(), pedido.getDataHoraPagamento(), pedido.getItens(), pedido.getStatus(), valorTotal, 0, 0, 0);

        // 3. Calculate discount
        BigDecimal desconto = descontoService.calcularDesconto(tempPedido);

        // 4. Calculate tax (based on value before discount)
        BigDecimal imposto = impostoService.calcularImposto(tempPedido);

        // 5. Calculate final cost: (valor - desconto) + imposto
        BigDecimal valorComDesconto = BigDecimal.valueOf(valorTotal).subtract(desconto);
        BigDecimal valorCobrado = valorComDesconto.add(imposto);

        // 6. Create the final approved pedido object
        Pedido pedidoAprovado = new Pedido(
            pedido.getId(),
            pedido.getCliente(),
            pedido.getDataHoraPagamento(),
            pedido.getItens(),
            Pedido.Status.APROVADO,
            valorTotal,
            imposto.doubleValue(),
            desconto.doubleValue(),
            valorCobrado.doubleValue()
        );

        return pedidoRepository.save(pedidoAprovado);
    }
}
