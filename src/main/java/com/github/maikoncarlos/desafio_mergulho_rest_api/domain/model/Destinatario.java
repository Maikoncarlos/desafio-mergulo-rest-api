package com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Embeddable
public class Destinatario {
    @NotBlank
    @Column(name = "destinatario_name")
    private String name;
    @NotBlank
    @Column(name = "destinatario_street")
    private String street;
    @NotBlank
    @Column(name = "destinatario_number")
    private String number;
    @NotBlank
    @Column(name = "destinatario_complement")
    private String complement;
    @NotBlank
    @Column(name = "destinatario_bairro")
    private String bairro;
}
