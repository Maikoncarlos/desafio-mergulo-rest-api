package com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.maikoncarlos.desafio_mergulho_rest_api.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;
    @Valid
    @ManyToOne
    @JoinColumn (name = "cliente_id")
    @ConvertGroup(to = ValidationGroups.ClienteId.class)
    private Cliente cliente;
    @Embedded
    @NotNull
    @Valid
    private Destinatario destinatario;
    @NotNull
    private BigDecimal taxa;
    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusEntrega status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataPedido;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;
}
