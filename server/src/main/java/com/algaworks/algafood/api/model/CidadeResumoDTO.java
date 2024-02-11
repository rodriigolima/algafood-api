package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeResumoDTO extends RepresentationModel<CidadeResumoDTO> {

	private Long id;

	private String nome;

	private String estado;

}