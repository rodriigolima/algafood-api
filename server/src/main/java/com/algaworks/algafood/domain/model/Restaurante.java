package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@Embedded
	private Endereco endereco;

	private Boolean ativo = Boolean.TRUE;

	private Boolean aberto = Boolean.FALSE;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();

	@OneToMany(mappedBy = "restaurante")
	private Set<Produto> produtos = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<>();

	public void ativar() {

		setAtivo(true);
	}

	public void inativar() {

		setAtivo(false);
	}

	public void abrir() {

		setAberto(true);
	}

	public void fechar() {

		setAberto(false);
	}

	public void removerFormaPagamento(FormaPagamento formaPagamento) {

		getFormasPagamento().remove(formaPagamento);
	}

	public void adicionarFormaPagamento(FormaPagamento formaPagamento) {

		getFormasPagamento().add(formaPagamento);
	}

	public void removerResponsavel(Usuario usuario) {

		getResponsaveis().remove(usuario);
	}

	public void adicionarResponsavel(Usuario usuario) {

		getResponsaveis().add(usuario);
	}

	public boolean aceitarFormaPagamento(FormaPagamento formaPagamento) {

		return getFormasPagamento().contains(formaPagamento);
	}

	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {

		return !aceitarFormaPagamento(formaPagamento);
	}

	public boolean isAberto() {

		return this.aberto;
	}

	public boolean isFechado() {

		return !isAberto();
	}

	public boolean isInativo() {

		return !isAtivo();
	}

	public boolean isAtivo() {

		return this.ativo;
	}

	public boolean aberturaPermitida() {

		return isAtivo() && isFechado();
	}

	public boolean ativacaoPermitida() {

		return isInativo();
	}

	public boolean inativacaoPermitida() {

		return isAtivo();
	}

	public boolean fechamentoPermitido() {

		return isAberto();
	}

}
