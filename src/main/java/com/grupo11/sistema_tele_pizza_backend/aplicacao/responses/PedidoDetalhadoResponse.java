package com.grupo11.sistema_tele_pizza_backend.aplicacao.responses;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDetalhadoResponse {
    private Long id;
    private Long clienteId;
    private LocalDateTime dataHoraPagamento;
    private String status;
    private double valor;
    private double impostos;
    private double desconto;
    private double valorCobrado;
    private List<String> itens;

    public PedidoDetalhadoResponse(Pedido pedido) {
        this.id = pedido.getId();
        this.clienteId = pedido.getCliente() != null ? pedido.getCliente().getId() : null;
        this.dataHoraPagamento = pedido.getDataHoraPagamento();
        this.status = pedido.getStatus().name();
        this.valor = pedido.getValor();
        this.impostos = pedido.getImpostos();
        this.desconto = pedido.getDesconto();
        this.valorCobrado = pedido.getValorCobrado();
        this.itens = pedido.getItens().stream()
                .map(item -> item.getItem().getDescricao() + " (x" + item.getQuantidade() + ")")
                .collect(Collectors.toList());
    }

    // Getters
    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public LocalDateTime getDataHoraPagamento() { return dataHoraPagamento; }
    public String getStatus() { return status; }
    public double getValor() { return valor; }
    public double getImpostos() { return impostos; }
    public double getDesconto() { return desconto; }
    public double getValorCobrado() { return valorCobrado; }
    public List<String> getItens() { return itens;}
}