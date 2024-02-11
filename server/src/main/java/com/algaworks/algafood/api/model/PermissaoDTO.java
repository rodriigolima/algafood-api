package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Setter
@Getter
public class PermissaoDTO extends RepresentationModel<PermissaoDTO> {

	private Long id;

	private String nome;

	private String descricao;

}
