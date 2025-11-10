package com.grupo11.sistema_tele_pizza_backend.dominio.dados;

import java.util.List;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Produto;

public interface ProdutosRepository {
    Produto recuperaProdutoPorid(long id);
    List<Produto> recuperaProdutosCardapio(long id);
    void marcarComoIndisponivel(long id);
}
