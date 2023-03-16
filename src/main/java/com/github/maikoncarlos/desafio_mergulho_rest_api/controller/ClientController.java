package com.github.maikoncarlos.desafio_mergulho_rest_api.controller;

import com.github.maikoncarlos.desafio_mergulho_rest_api.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    @GetMapping
    public Cliente getClientList() {
        return Cliente.builder()
                .id(1L)
                .name("Maikon")
                .email("maikon@gmail.com")
                .phone("119578898000")
                .build();
    }
}
