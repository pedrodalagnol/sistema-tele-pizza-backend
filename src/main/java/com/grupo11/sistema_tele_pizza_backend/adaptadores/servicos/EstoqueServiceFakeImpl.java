package com.grupo11.sistema_tele_pizza_backend.adaptadores.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.ItemPedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.EstoqueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueServiceFakeImpl implements EstoqueService {
    @Override
    public boolean verificaDisponibilidade(List<ItemPedido> itens) {
        return true;
    }
}
