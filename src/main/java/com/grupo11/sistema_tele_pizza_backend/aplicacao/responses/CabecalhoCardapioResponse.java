package com.grupo11.sistema_tele_pizza_backend.aplicacao.responses;

import java.util.List;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.CabecalhoCardapio;

public record CabecalhoCardapioResponse(List<CabecalhoCardapio> cabecalhos) {
}
