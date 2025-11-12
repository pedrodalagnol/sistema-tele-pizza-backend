package com.grupo11.sistema_tele_pizza_backend.adaptadores.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.ImpostoService;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final ImpostoService impostoService;
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServiceImpl(ImpostoService impostoService, PedidoRepository pedidoRepository) {
        this.impostoService = impostoService;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido submeterPedido(Pedido pedido) {
        // 1. Calculate total value
        double valorTotal = pedido.getItens().stream()
                .mapToDouble(item -> item.getItem().getPreco() * item.getQuantidade())
                .sum();

        // Create a temporary Pedido with the value to calculate discount and tax
        Pedido tempPedido = new Pedido(pedido.getId(), pedido.getCliente(), pedido.getDataHoraPagamento(), pedido.getItens(), pedido.getStatus(), valorTotal, 0, 0, 0);

        // 2. Calculate discount (removed)
        BigDecimal desconto = BigDecimal.ZERO;

        // 3. Calculate tax (based on value before discount)
        BigDecimal imposto = impostoService.calcularImposto(tempPedido);

        // 4. Calculate final cost: (valor - desconto) + imposto
        BigDecimal valorComDesconto = BigDecimal.valueOf(valorTotal).subtract(desconto);
        BigDecimal valorCobrado = valorComDesconto.add(imposto);

        // 5. Create the final approved pedido object
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
