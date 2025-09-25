package com.grupo11.sistema_tele_pizza_backend.aplicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.CabecalhoCardapioResponse;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.CardapioService;

@Component
public class RecuperaListaCardapiosUC {
    private CardapioService cardapioService;

    @Autowired
    public RecuperaListaCardapiosUC(CardapioService cardapioService){
        this.cardapioService = cardapioService;
    }

    public CabecalhoCardapioResponse run(){
        return new CabecalhoCardapioResponse(cardapioService.recuperaListaDeCardapios());
    }    
}
