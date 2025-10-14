package com.grupo11.sistema_tele_pizza_backend.adaptadores.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.ClienteRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.ProdutosRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.ItemPedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Produto;
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

        // In a real application, you would not mutate the object directly.
        // You would create a new one or use a builder.
        // new Pedido(...)

        // 3. Calculate discount
        BigDecimal desconto = descontoService.calcularDesconto(pedido);

        // 4. Calculate tax
        BigDecimal imposto = impostoService.calcularImposto(pedido);

        // 5. Calculate final cost
        BigDecimal valorCobrado = BigDecimal.valueOf(valorTotal).subtract(desconto).add(imposto);

        // 6. Set status and save
        pedido.setStatus(Pedido.Status.APROVADO);
        // Set other calculated values if your Pedido entity has setters or a builder

        return pedidoRepository.save(pedido);
    }
}
