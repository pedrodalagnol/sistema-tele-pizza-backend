package com.grupo11.sistema_tele_pizza_backend.adaptadores.notificacao;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Pedido;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    public PedidoPublisher(RabbitTemplate rabbitTemplate, @Value("${app.rabbitmq.queue.name}") String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public void enviarParaEntrega(Pedido pedido) {
        rabbitTemplate.convertAndSend(queueName, pedido);
    }
}
