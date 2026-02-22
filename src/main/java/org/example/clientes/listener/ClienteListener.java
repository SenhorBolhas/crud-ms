package org.example.clientes.listener;

import org.example.clientes.entity.Cliente;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class ClienteListener {

    @RabbitListener(queues = "cliente.queue")
    public void consumir(Cliente cliente) {
        System.out.println("Mensagem recebida: " + cliente.getNome());
    }

}
