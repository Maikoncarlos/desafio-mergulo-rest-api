package com.github.maikoncarlos.desafio_mergulho_rest_api.domain.service;

import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Cliente;
import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Entrega;
import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.StatusEntrega;
import com.github.maikoncarlos.desafio_mergulho_rest_api.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SolicitacaoEntregaService {

    private final EntregaRepository entregaRepository;
    private final CatalogClientService catalogClientService;

    @Transactional
    public Entrega solicitar(Entrega entrega){
        Cliente cliente = catalogClientService.getCliente(entrega);

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());

        return entregaRepository.save(entrega);
    }


}
