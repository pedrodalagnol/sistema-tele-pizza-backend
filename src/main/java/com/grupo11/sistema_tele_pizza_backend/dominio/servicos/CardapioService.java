package com.grupo11.sistema_tele_pizza_backend.dominio.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.CardapioRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.CabecalhoCardapio;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cardapio;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Produto;

@Service
public class CardapioService {
    private CardapioRepository cardapioRepository;

    @Autowired
    public CardapioService(CardapioRepository cardapioRepository){
        this.cardapioRepository = cardapioRepository;
    }

    public Cardapio recuperaCardapio(long Id){
        return cardapioRepository.recuperaPorId(Id);
    }

    public List<CabecalhoCardapio> recuperaListaDeCardapios(){
        return cardapioRepository.cardapiosDisponiveis();
    }

    public List<Produto> recuperaSugestoesDoChef(){
        return cardapioRepository.indicacoesDoChef();
    }
}
