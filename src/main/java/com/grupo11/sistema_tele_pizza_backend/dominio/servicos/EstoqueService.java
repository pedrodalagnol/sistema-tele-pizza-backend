package com.grupo11.sistema_tele_pizza_backend.dominio.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.ItemPedido;

import java.util.List;

public interface EstoqueService {
    List<ItemPedido> verificaDisponibilidade(List<ItemPedido> itens);
}
