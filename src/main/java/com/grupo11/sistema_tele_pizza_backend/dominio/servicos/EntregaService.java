package com.grupo11.sistema_tele_pizza_backend.dominio.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;

public interface EntregaService {
    void enviarParaEntrega(Pedido pedido);
}
