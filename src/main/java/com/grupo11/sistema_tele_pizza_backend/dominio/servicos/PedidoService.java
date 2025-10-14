package com.grupo11.sistema_tele_pizza_backend.dominio.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;

public interface PedidoService {
    Pedido aprovarPedido(Pedido pedido);
}
