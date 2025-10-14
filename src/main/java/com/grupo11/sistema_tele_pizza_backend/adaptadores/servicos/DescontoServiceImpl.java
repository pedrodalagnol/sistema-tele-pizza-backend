package com.grupo11.sistema_tele_pizza_backend.adaptadores.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.DescontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DescontoServiceImpl implements DescontoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public DescontoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public BigDecimal calcularDesconto(Pedido pedido) {
        LocalDateTime vinteDiasAtras = LocalDateTime.now().minusDays(20);
        List<Pedido> pedidosRecentes = pedidoRepository.findPedidosByClienteAndData(pedido.getCliente().getId(), vinteDiasAtras);

        if (pedidosRecentes.size() > 3) {
            return BigDecimal.valueOf(pedido.getValor()).multiply(BigDecimal.valueOf(0.07));
        }

        return BigDecimal.ZERO;
    }
}
