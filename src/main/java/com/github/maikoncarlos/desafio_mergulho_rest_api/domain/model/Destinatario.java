package com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Destinatario {
    @Column(name = "destinatario_name")
    private String name;
    @Column(name = "destinatario_street")
    private String street;
    @Column(name = "destinatario_number")
    private String number;
    @Column(name = "destinatario_complement")
    private String complement;
    @Column(name = "destinatario_bairro")
    private String bairro;
}
