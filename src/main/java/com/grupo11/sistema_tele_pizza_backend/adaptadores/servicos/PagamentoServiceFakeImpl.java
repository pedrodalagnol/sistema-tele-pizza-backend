package com.grupo11.sistema_tele_pizza_backend.adaptadores.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.PagamentoService;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceFakeImpl implements PagamentoService {
    @Override
    public boolean processarPagamento(Pedido pedido) {
        // Simulate a successful payment
        return true;
    }
}
