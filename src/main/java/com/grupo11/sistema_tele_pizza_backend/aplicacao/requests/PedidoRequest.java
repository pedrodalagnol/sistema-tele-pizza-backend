package com.grupo11.sistema_tele_pizza_backend.aplicacao.requests;

import java.util.List;

public class PedidoRequest {
    private Long clienteId;
    private String endereco;
    private List<ItemPedidoRequest> itens;

    public PedidoRequest(Long clienteId, String endereco, List<ItemPedidoRequest> itens) {
        this.clienteId = clienteId;
        this.endereco = endereco;
        this.itens = itens;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<ItemPedidoRequest> getItens() {
        return itens;
    }
}
