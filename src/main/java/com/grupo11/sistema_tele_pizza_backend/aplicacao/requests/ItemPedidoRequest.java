package com.grupo11.sistema_tele_pizza_backend.aplicacao.requests;

public class ItemPedidoRequest {
    private Long produtoId;
    private int quantidade;

    public ItemPedidoRequest(Long produtoId, int quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
