package com.grupo11.sistema_tele_pizza_backend.dominio.dados;

import java.util.List;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.CabecalhoCardapio;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cardapio;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Produto;

public interface CardapioRepository {
    List<CabecalhoCardapio> cardapiosDisponiveis();
    Cardapio recuperaPorId(long id);
    List<Produto> indicacoesDoChef();
}
