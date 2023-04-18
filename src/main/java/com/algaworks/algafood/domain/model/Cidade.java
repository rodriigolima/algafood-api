package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.core.validation.Groups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Valid
    @ConvertGroup(from = Default.class,to = Groups.EstadoId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estado estado;
}
