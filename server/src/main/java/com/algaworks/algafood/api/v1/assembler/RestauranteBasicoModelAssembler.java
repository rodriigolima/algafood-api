package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoDTO;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteBasicoModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public RestauranteBasicoModelAssembler() {

		super(RestauranteController.class, RestauranteBasicoDTO.class);
	}

	@Override
	public RestauranteBasicoDTO toModel(Restaurante restaurante) {

		RestauranteBasicoDTO dto = createModelWithId(restaurante.getId(), restaurante);

		modelMapper.map(restaurante, dto);

		dto.add(algaLinks.linkToRestaurantes("restaurantes"));

		dto.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));

		return dto;
	}

	@Override
	public CollectionModel<RestauranteBasicoDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {

		return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
	}

}
