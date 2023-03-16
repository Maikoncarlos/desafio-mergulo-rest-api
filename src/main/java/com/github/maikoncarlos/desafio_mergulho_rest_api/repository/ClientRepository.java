package com.github.maikoncarlos.desafio_mergulho_rest_api.repository;

import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByName(String name);

    List<Cliente> findByNameContains(String name);
}
