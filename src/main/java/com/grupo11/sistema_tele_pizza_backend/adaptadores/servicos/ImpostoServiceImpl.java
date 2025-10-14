package com.grupo11.sistema_tele_pizza_backend.adaptadores.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.ImpostoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ImpostoServiceImpl implements ImpostoService {
    @Override
    public BigDecimal calcularImposto(Pedido pedido) {
        return BigDecimal.valueOf(pedido.getValor()).multiply(BigDecimal.valueOf(0.10));
    }
}
