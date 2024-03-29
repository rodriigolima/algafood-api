package com.algaworks.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteDTO extends RepresentationModel<RestauranteDTO> {

	private Long id;

	private String nome;

	private BigDecimal taxaFrete;

	private CozinhaDTO cozinha;

	private Boolean ativo;

	private Boolean aberto;

	private EnderecoDTO endereco;

}
