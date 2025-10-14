package com.grupo11.sistema_tele_pizza_backend.aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.CardapioResponse;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cardapio;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Produto;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.CardapioService;

@Component
public class RecuperarCardapioUC {
    private CardapioService cardapioService;

    @Autowired
    public RecuperarCardapioUC(CardapioService cardapioService){
        this.cardapioService = cardapioService;
    }

    public CardapioResponse run(long idCardapio){
        Cardapio cardapio = cardapioService.recuperaCardapio(1L);
        List<Produto> sugestoes = cardapioService.recuperaSugestoesDoChef();
        return new CardapioResponse(cardapio,sugestoes);
    }
}
