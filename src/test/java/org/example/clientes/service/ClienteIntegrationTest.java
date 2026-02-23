package org.example.clientes.service;

import org.example.clientes.entity.Cliente;
import org.example.clientes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteIntegrationTest {

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteRepository repository;

    @BeforeEach
    void limparBanco() {
        repository.deleteAll();
    }

    private static Long clienteId = 1L;

    Cliente cliente = Cliente.builder()
            .nome("Giovanni")
            .email("gio@email.com")
            .build();

    @Test
    void deveCriarCliente() {
        Cliente cliente = Cliente.builder()
                .nome("Giovanni")
                .email("gio1@email.com")
                .build();

        Cliente salvo = service.criar(cliente);

        assertNotNull(salvo.getId());
        assertEquals("Giovanni", salvo.getNome());
    }

    @Test
    void deveListarClientes() {
        Cliente cliente = Cliente.builder()
                .nome("Maria")
                .email("maria@email.com")
                .build();

        service.criar(cliente);

        List<Cliente> clientes = service.listar();

        assertFalse(clientes.isEmpty());
    }

    @Test
    void deveAtualizarCliente() {
        Cliente salvo = service.criar(
                Cliente.builder()
                        .nome("João")
                        .email("joao@email.com")
                        .build()
        );

        Cliente atualizado = Cliente.builder()
                .nome("João Atualizado")
                .email("joao2@email.com")
                .build();

        Cliente resultado = service.atualizar(salvo.getId(), atualizado);

        assertEquals("João Atualizado", resultado.getNome());
    }

    @Test
    void deveBuscarPorNome() {
        service.criar(
                Cliente.builder()
                        .nome("Carlos")
                        .email("carlos@email.com")
                        .build()
        );

        List<Cliente> resultado = repository.buscarPorNome("Carlos");

        assertFalse(resultado.isEmpty());
    }

    @Test
    void deveDeletarCliente() {
        Cliente salvo = service.criar(
                Cliente.builder()
                        .nome("Pedro")
                        .email("pedro@email.com")
                        .build()
        );

        service.deletar(salvo.getId());

        assertTrue(repository.findById(salvo.getId()).isEmpty());
    }
}