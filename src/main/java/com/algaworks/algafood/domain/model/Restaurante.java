package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.core.validation.TaxaFrete;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotBlank
    private String nome;
    
    @Column(name = "taxa_frete", nullable = false)
    @NotNull
    //@PositiveOrZero
    @TaxaFrete
    private BigDecimal taxaFrete;

    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @ManyToOne //(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;
    
    @Embedded
    private Endereco endereco;
    
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime  dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime  dataAtualizacao;
    
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento", 
        joinColumns = @JoinColumn(name = "restaurante_id"),
        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();
    
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();
    
}
