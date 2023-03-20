package com.github.maikoncarlos.desafio_mergulho_rest_api.controller;

import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Entrega;
import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.service.SolicitacaoEntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/entregas")
@RequiredArgsConstructor
public class EntregaController {
    private final SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    public ResponseEntity<Entrega> solicitar(@RequestBody Entrega entrega){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitacaoEntregaService.solicitar(entrega));
    }
}
