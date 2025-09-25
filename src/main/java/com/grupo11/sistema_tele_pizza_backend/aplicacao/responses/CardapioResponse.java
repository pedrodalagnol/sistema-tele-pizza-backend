package com.grupo11.sistema_tele_pizza_backend.aplicacao.responses;

import java.util.List;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cardapio;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Produto;

public class CardapioResponse {
    private Cardapio cardapio;
    private List<Produto> sugestoesDoChef;
    
    public CardapioResponse(Cardapio cardapio, List<Produto> sugestoesDoChef) {
        this.cardapio = cardapio;
        this.sugestoesDoChef = sugestoesDoChef;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public List<Produto> getSugestoesDoChef() {
        return sugestoesDoChef;
    }
}
