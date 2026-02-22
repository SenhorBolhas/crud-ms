package org.example.clientes.repository;

import org.example.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    @Query("SELECT c FROM Cliente c WHERE lower(c.nome) LIKE lower(concat('%', :nome, '%'))")
    List<Cliente> buscarPorNome(@Param("nome") String nome);
}