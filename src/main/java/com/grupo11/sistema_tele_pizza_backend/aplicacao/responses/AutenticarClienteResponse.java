package com.grupo11.sistema_tele_pizza_backend.aplicacao.responses;

public class AutenticarClienteResponse {
    private Long clienteId;

    public AutenticarClienteResponse(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getClienteId() {
        return clienteId;
    }
}