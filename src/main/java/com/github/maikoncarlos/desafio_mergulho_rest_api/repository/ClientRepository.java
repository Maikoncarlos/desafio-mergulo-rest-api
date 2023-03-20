package com.github.maikoncarlos.desafio_mergulho_rest_api.repository;

import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNameContains(String name);
    Optional<Cliente> findByEmail(String email);
}
