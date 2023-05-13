package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;
    
    @Embedded
    private Endereco endereco;
    
    private Boolean ativo = Boolean.TRUE;
    
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime  dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;
    
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento", 
        joinColumns = @JoinColumn(name = "restaurante_id"),
        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>();
    
    @OneToMany(mappedBy = "restaurante")
    private Set<Produto> produtos = new HashSet<>();
    
    public void ativar() {
        setAtivo(true);
    }
    
    public void inativar() {
        setAtivo(false);
    }
    
    public void removerFormaPagamento(FormaPagamento formaPagamento) {
        getFormasPagamento().remove(formaPagamento);
    }

    public void adicionarFormaPagamento(FormaPagamento formaPagamento) {
        getFormasPagamento().add(formaPagamento);
    }
    
}
