package org.example.clientes.service;

import org.example.clientes.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ClienteServiceTest {

    @Autowired
    ClienteService service;

    @Test
    void deveCriarCliente() {
        Cliente cliente = Cliente.builder()
                .nome("Giovanni")
                .email("gio@email.com")
                .build();

        Cliente salvo = service.criar(cliente);

        assertNotNull(salvo.getId());
    }
}
