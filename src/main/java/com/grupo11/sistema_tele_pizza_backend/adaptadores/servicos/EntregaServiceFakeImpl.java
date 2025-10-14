package com.grupo11.sistema_tele_pizza_backend.adaptadores.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class EntregaServiceFakeImpl implements EntregaService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public EntregaServiceFakeImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void enviarParaEntrega(Pedido pedido) {
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            pedido.setStatus(Pedido.Status.TRANSPORTE);
            pedidoRepository.save(pedido);

            Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                pedido.setStatus(Pedido.Status.ENTREGUE);
                pedidoRepository.save(pedido);
            }, 5, TimeUnit.SECONDS); // Simulate delivery time

        }, 5, TimeUnit.SECONDS); // Simulate time to assign a delivery person
    }
}
