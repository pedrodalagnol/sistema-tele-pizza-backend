package com.grupo11.sistema_tele_pizza_backend.dominio.servicos;

import com.grupo11.sistema_tele_pizza_backend.adaptadores.notificacao.PedidoPublisher;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.PedidoRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class CozinhaService {
    private final PedidoRepository pedidoRepository;
    private final PedidoPublisher pedidoPublisher;
    private final Queue<Pedido> filaEntrada;
    private Pedido emPreparacao;
    private final ScheduledExecutorService scheduler;

    @Autowired
    public CozinhaService(PedidoRepository pedidoRepository, PedidoPublisher pedidoPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoPublisher = pedidoPublisher;
        this.filaEntrada = new LinkedBlockingQueue<>();
        this.emPreparacao = null;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    private synchronized void colocaEmPreparacao(Pedido pedido) {
        pedido.setStatus(Pedido.Status.PREPARACAO);
        pedidoRepository.save(pedido);
        emPreparacao = pedido;
        System.out.println("Pedido em preparacao: " + pedido.getId());
        scheduler.schedule(this::pedidoPronto, 5, TimeUnit.SECONDS);
    }

    public synchronized void chegadaDePedido(Pedido p) {
        p.setStatus(Pedido.Status.AGUARDANDO);
        pedidoRepository.save(p);
        filaEntrada.add(p);
        System.out.println("Pedido na fila de entrada da cozinha: " + p.getId());
        if (emPreparacao == null) {
            colocaEmPreparacao(filaEntrada.poll());
        }
    }

    public synchronized void pedidoPronto() {
        emPreparacao.setStatus(Pedido.Status.PRONTO);
        pedidoRepository.save(emPreparacao);
        System.out.println("Pedido pronto: " + emPreparacao.getId());
        
        // Envia para entrega
        pedidoPublisher.enviarParaEntrega(emPreparacao);

        emPreparacao = null;
        if (!filaEntrada.isEmpty()) {
            Pedido prox = filaEntrada.poll();
            scheduler.schedule(() -> colocaEmPreparacao(prox), 1, TimeUnit.SECONDS);
        }
    }
}
