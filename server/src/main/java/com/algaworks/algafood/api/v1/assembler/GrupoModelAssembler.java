package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.GrupoController;
import com.algaworks.algafood.api.v1.model.GrupoDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity security;

	public GrupoModelAssembler() {

		super(GrupoController.class, GrupoDTO.class);
	}

	@Override
	public GrupoDTO toModel(Grupo grupo) {

		GrupoDTO dto = createModelWithId(grupo.getId(), grupo);
		modelMapper.map(grupo, dto);

		if (this.security.podeConsultarUsuariosGruposPermissoes()) {
			dto.add(algaLinks.linkToGrupos("grupos"));

			dto.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
		}

		return dto;
	}

	@Override
	public CollectionModel<GrupoDTO> toCollectionModel(Iterable<? extends Grupo> entities) {

		CollectionModel<GrupoDTO> dto = super.toCollectionModel(entities);

		if (this.security.podeConsultarUsuariosGruposPermissoes())
			dto.add(algaLinks.linkToGrupos());

		return dto;
	}
}
