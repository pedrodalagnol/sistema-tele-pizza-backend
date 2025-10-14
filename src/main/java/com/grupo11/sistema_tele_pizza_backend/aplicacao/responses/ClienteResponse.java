package com.grupo11.sistema_tele_pizza_backend.aplicacao.responses;

public class ClienteResponse {
    private Long clienteId;

    public ClienteResponse(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getClienteId() {
        return clienteId;
    }
}
