package com.grupo11.sistema_tele_pizza_backend.adaptadores.apresentacao;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.CancelarPedidoUC;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.ConsultarStatusPedidoUC;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.PagarPedidoUC;
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
    private final CancelarPedidoUC cancelarPedidoUC;
    private final PagarPedidoUC pagarPedidoUC;

    @Autowired
    public PedidoController(SubmeterPedidoUC submeterPedidoUC, ConsultarStatusPedidoUC consultarStatusPedidoUC, CancelarPedidoUC cancelarPedidoUC, PagarPedidoUC pagarPedidoUC) {
        this.submeterPedidoUC = submeterPedidoUC;
        this.consultarStatusPedidoUC = consultarStatusPedidoUC;
        this.cancelarPedidoUC = cancelarPedidoUC;
        this.pagarPedidoUC = pagarPedidoUC;
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

    @DeleteMapping("/{id}/cancelar")
    @CrossOrigin("*")
    public void cancelarPedido(@PathVariable("id") Long pedidoId) {
        cancelarPedidoUC.run(pedidoId);
    }

    @PostMapping("/{id}/pagar")
    @CrossOrigin("*")
    public void pagarPedido(@PathVariable("id") Long pedidoId) {
        pagarPedidoUC.run(pedidoId);
    }
}
