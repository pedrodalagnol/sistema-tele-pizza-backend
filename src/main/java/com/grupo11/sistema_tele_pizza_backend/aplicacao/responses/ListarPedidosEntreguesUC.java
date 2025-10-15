package com.grupo11.sistema_tele_pizza_backend.aplicacao;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.PedidoDetalhadoResponse;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListarPedidosEntreguesUC {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public ListarPedidosEntreguesUC(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoDetalhadoResponse> run(LocalDateTime inicio, LocalDateTime fim) {
        return pedidoRepository.findPedidosEntreguesByData(inicio, fim).stream()
                .map(PedidoDetalhadoResponse::new)
                .collect(Collectors.toList());
    }
}