package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity security;

	public RestauranteApenasNomeModelAssembler() {

		super(RestauranteController.class, RestauranteApenasNomeDTO.class);
	}

	@Override
	public RestauranteApenasNomeDTO toModel(Restaurante restaurante) {

		RestauranteApenasNomeDTO dto = createModelWithId(restaurante.getId(), restaurante);

		modelMapper.map(restaurante, dto);

		if (this.security.podeConsultarRestaurantes())
			dto.add(algaLinks.linkToRestaurantes("restaurantes"));

		return dto;
	}

	@Override
	public CollectionModel<RestauranteApenasNomeDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {

		CollectionModel<RestauranteApenasNomeDTO> dto = super.toCollectionModel(entities);

		if (this.security.podeConsultarRestaurantes())
			dto.add(algaLinks.linkToRestaurantes());

		return dto;
	}

}
