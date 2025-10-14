package com.grupo11.sistema_tele_pizza_backend.aplicacao.responses;

import java.math.BigDecimal;

public class PedidoResponse {
    private Long pedidoId;
    private String status;
    private BigDecimal custoTotal;

    public PedidoResponse(Long pedidoId, String status, BigDecimal custoTotal) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.custoTotal = custoTotal;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getCustoTotal() {
        return custoTotal;
    }
}
