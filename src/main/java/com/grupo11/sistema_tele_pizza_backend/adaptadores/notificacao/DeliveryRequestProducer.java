package com.grupo11.sistema_tele_pizza_backend.adaptadores.notificacao;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeliveryRequestProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public DeliveryRequestProducer(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}
