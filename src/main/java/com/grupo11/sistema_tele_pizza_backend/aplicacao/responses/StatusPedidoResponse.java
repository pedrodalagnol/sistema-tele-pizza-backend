package com.grupo11.sistema_tele_pizza_backend.aplicacao.responses;

public class StatusPedidoResponse {
    private Long pedidoId;
    private String status;

    public StatusPedidoResponse(Long pedidoId, String status) {
        this.pedidoId = pedidoId;
        this.status = status;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public String getStatus() {
        return status;
    }
}
