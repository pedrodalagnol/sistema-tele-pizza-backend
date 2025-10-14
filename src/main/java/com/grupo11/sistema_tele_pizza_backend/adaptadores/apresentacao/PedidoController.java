package com.grupo11.sistema_tele_pizza_backend.adaptadores.apresentacao;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.ConsultarStatusPedidoUC;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.SubmeterPedidoUC;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.requests.PedidoRequest;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.PedidoResponse;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.StatusPedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final SubmeterPedidoUC submeterPedidoUC;
    private final ConsultarStatusPedidoUC consultarStatusPedidoUC;

    @Autowired
    public PedidoController(SubmeterPedidoUC submeterPedidoUC, ConsultarStatusPedidoUC consultarStatusPedidoUC) {
        this.submeterPedidoUC = submeterPedidoUC;
        this.consultarStatusPedidoUC = consultarStatusPedidoUC;
    }

    @PostMapping
    @CrossOrigin("*")
    public PedidoResponse submeterPedido(@RequestBody PedidoRequest pedidoRequest) {
        return submeterPedidoUC.run(pedidoRequest);
    }

    @GetMapping("/{id}/status")
    @CrossOrigin("*")
    public StatusPedidoResponse consultarStatus(@PathVariable("id") Long pedidoId) {
        return consultarStatusPedidoUC.run(pedidoId);
    }
}
