package com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}