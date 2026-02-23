package org.example.clientes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class MsClientesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsClientesApplication.class, args);
    }
}