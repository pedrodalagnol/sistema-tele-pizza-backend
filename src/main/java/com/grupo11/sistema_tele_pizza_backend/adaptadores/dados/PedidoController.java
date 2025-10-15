package com.grupo11.sistema_tele_pizza_backend.adaptadores.apresentacao;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.*;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.requests.PedidoRequest;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.PedidoDetalhadoResponse;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.PedidoResponse;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.StatusPedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final SubmeterPedidoUC submeterPedidoUC;
    private final ConsultarStatusPedidoUC consultarStatusPedidoUC;
    private final CancelarPedidoUC cancelarPedidoUC;
    private final PagarPedidoUC pagarPedidoUC;
    private final ListarPedidosEntreguesUC listarPedidosEntreguesUC;
    private final ListarPedidosPorClienteUC listarPedidosPorClienteUC;

    @Autowired
    public PedidoController(SubmeterPedidoUC submeterPedidoUC, ConsultarStatusPedidoUC consultarStatusPedidoUC, CancelarPedidoUC cancelarPedidoUC, PagarPedidoUC pagarPedidoUC, ListarPedidosEntreguesUC listarPedidosEntreguesUC, ListarPedidosPorClienteUC listarPedidosPorClienteUC) {
        this.submeterPedidoUC = submeterPedidoUC;
        this.consultarStatusPedidoUC = consultarStatusPedidoUC;
        this.cancelarPedidoUC = cancelarPedidoUC;
        this.pagarPedidoUC = pagarPedidoUC;
        this.listarPedidosEntreguesUC = listarPedidosEntreguesUC;
        this.listarPedidosPorClienteUC = listarPedidosPorClienteUC;
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

    @GetMapping("/entregues")
    @CrossOrigin("*")
    public List<PedidoDetalhadoResponse> listarPedidosEntregues(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return listarPedidosEntreguesUC.run(inicio, fim);
    }

    @GetMapping("/entregues/cliente/{clienteId}")
    @CrossOrigin("*")
    public List<PedidoDetalhadoResponse> listarPedidosPorCliente(
            @PathVariable Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return listarPedidosPorClienteUC.run(clienteId, inicio, fim);
    }
}