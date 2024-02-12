package com.algaworks.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteBasicoDTO extends RepresentationModel<RestauranteBasicoDTO> {

	private Long id;

	private String nome;

	private BigDecimal taxaFrete;

	private CozinhaDTO cozinha;

}
