package com.github.maikoncarlos.desafio_mergulho_rest_api.controller;

import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Entrega;
import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.service.SolicitacaoEntregaService;
import com.github.maikoncarlos.desafio_mergulho_rest_api.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/entregas")
@RequiredArgsConstructor
public class EntregaController {
    private final SolicitacaoEntregaService solicitacaoEntregaService;
    private final EntregaRepository entregaRepository;

    @PostMapping
    public ResponseEntity<Entrega> solicitar(@RequestBody @Valid Entrega entrega){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitacaoEntregaService.solicitar(entrega));
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> entregaList(){
        return ResponseEntity.ok().body(entregaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> getEntrega(@PathVariable("id") Long entregaId){
        return entregaRepository.findById(entregaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
