package com.grupo11.sistema_tele_pizza_backend.aplicacao.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.ItemPedido;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmeterPedidoResponse {

    private PedidoResponse pedido;
    private List<ItemPedido> itensIndisponiveis;
    private String mensagem;

    public SubmeterPedidoResponse(PedidoResponse pedido) {
        this.pedido = pedido;
    }

    public SubmeterPedidoResponse(List<ItemPedido> itensIndisponiveis, String mensagem) {
        this.itensIndisponiveis = itensIndisponiveis;
        this.mensagem = mensagem;
    }

    public PedidoResponse getPedido() {
        return pedido;
    }

    public List<ItemPedido> getItensIndisponiveis() {
        return itensIndisponiveis;
    }

    public String getMensagem() {
        return mensagem;
    }
}
