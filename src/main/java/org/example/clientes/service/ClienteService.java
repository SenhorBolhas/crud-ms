package org.example.clientes.service;

import lombok.RequiredArgsConstructor;
import org.example.clientes.entity.Cliente;
import org.example.clientes.repository.ClienteRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public Cliente criar(Cliente cliente) {
        Cliente salvo = repository.save(cliente);
        rabbitTemplate.convertAndSend("cliente.exchange", "cliente.criado", salvo);
        return salvo;
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente existente = repository.findById(id)
                .orElseThrow();
        existente.setNome(cliente.getNome());
        existente.setEmail(cliente.getEmail());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}