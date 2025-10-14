package com.grupo11.sistema_tele_pizza_backend.dominio.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;

import java.math.BigDecimal;

public interface ImpostoService {
    BigDecimal calcularImposto(Pedido pedido);
}
