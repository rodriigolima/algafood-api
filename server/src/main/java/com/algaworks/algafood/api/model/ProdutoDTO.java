package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProdutoDTO {

	private Long id;

	private String nome;

	private String descricao;

	private BigDecimal preco;

	private Boolean ativo;

}
