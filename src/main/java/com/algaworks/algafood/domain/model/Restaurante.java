package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
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
    //@NotNull
    //@NotEmpty
    @NotBlank
    private String nome;
    
    @Column(name = "taxa_frete", nullable = false)
    //@DecimalMin("0")
    @PositiveOrZero
    private BigDecimal taxaFrete;
    
    //@JsonIgnore
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @ManyToOne //(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;
    
    @JsonIgnore
    @Embedded
    private Endereco endereco;
    
    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime  dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime  dataAtualizacao;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento", 
        joinColumns = @JoinColumn(name = "restaurante_id"),
        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();
    
}
