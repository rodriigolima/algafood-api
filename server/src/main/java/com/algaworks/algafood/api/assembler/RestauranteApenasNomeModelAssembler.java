package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.RestauranteApenasNomeDTO;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteApenasNomeModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public RestauranteApenasNomeModelAssembler() {

		super(RestauranteController.class, RestauranteApenasNomeDTO.class);
	}

	@Override
	public RestauranteApenasNomeDTO toModel(Restaurante restaurante) {

		RestauranteApenasNomeDTO dto = createModelWithId(restaurante.getId(), restaurante);

		modelMapper.map(restaurante, dto);

		dto.add(algaLinks.linkToRestaurantes("restaurantes"));

		return dto;
	}

	@Override
	public CollectionModel<RestauranteApenasNomeDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {

		return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
	}

}
