package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "grupos")
@Setter
@Getter
public class GrupoDTO extends RepresentationModel<GrupoDTO> {

	private Long id;

	private String nome;

	// private Permissao permissoes;
}
