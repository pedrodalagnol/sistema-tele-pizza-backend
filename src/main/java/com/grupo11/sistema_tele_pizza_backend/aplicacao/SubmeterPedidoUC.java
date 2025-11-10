package com.grupo11.sistema_tele_pizza_backend.aplicacao;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.requests.PedidoRequest;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.PedidoResponse;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.SubmeterPedidoResponse;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.ClienteRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.ProdutosRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cliente;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.ItemPedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Produto;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.EstoqueService;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubmeterPedidoUC {

    private final PedidoService pedidoService;
    private final ClienteRepository clienteRepository;
    private final ProdutosRepository produtosRepository;
    private final EstoqueService estoqueService;

    @Autowired
    public SubmeterPedidoUC(PedidoService pedidoService, ClienteRepository clienteRepository, ProdutosRepository produtosRepository, EstoqueService estoqueService) {
        this.pedidoService = pedidoService;
        this.clienteRepository = clienteRepository;
        this.produtosRepository = produtosRepository;
        this.estoqueService = estoqueService;
    }

    public SubmeterPedidoResponse run(PedidoRequest pedidoRequest) {
        Cliente cliente = clienteRepository.findById(pedidoRequest.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        List<ItemPedido> itens = pedidoRequest.getItens().stream()
                .map(itemReq -> {
                    Produto produto = produtosRepository.recuperaProdutoPorid(itemReq.getProdutoId());
                    if (produto == null) {
                        throw new IllegalArgumentException("Produto não encontrado: " + itemReq.getProdutoId());
                    }
                    return new ItemPedido(produto, itemReq.getQuantidade());
                })
                .collect(Collectors.toList());

        List<ItemPedido> itensIndisponiveis = estoqueService.verificaDisponibilidade(itens);
        if (!itensIndisponiveis.isEmpty()) {
            itensIndisponiveis.forEach(item -> produtosRepository.marcarComoIndisponivel(item.getItem().getId()));
            return new SubmeterPedidoResponse(itensIndisponiveis, "Itens do pedido indisponíveis no estoque.");
        }

        Pedido novoPedido = new Pedido(0, cliente, LocalDateTime.now(), itens, Pedido.Status.NOVO, 0, 0, 0, 0);

        Pedido pedidoAprovado = pedidoService.submeterPedido(novoPedido);

        return new SubmeterPedidoResponse(new PedidoResponse(pedidoAprovado.getId(), pedidoAprovado.getStatus().name(), null));
    }
}
