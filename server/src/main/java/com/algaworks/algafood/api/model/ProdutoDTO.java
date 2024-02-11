package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "produtos")
@Setter
@Getter
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> {

	private Long id;

	private String nome;

	private String descricao;

	private BigDecimal preco;

	private Boolean ativo;

}
