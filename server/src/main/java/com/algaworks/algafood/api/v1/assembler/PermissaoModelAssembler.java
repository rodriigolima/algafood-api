package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.model.PermissaoDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Permissao;

@Component
public class PermissaoModelAssembler implements RepresentationModelAssembler<Permissao, PermissaoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity security;

	@Override
	public PermissaoDTO toModel(Permissao permissao) {

		PermissaoDTO PermissaoDTO = modelMapper.map(permissao, PermissaoDTO.class);
		return PermissaoDTO;
	}

	@Override
	public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends Permissao> entities) {

		CollectionModel<PermissaoDTO> dto = RepresentationModelAssembler.super.toCollectionModel(entities);

		if (this.security.podeConsultarUsuariosGruposPermissoes())
			dto.add(algaLinks.linkToPermissoes());

		return dto;
	}
}
