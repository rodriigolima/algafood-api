package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.RestauranteDTO;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public RestauranteModelAssembler() {

		super(RestauranteController.class, RestauranteDTO.class);
	}

	@Override
	public RestauranteDTO toModel(Restaurante restaurante) {

		RestauranteDTO dto = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, dto);

		dto.add(algaLinks.linkToRestaurantes("restaurantes"));

		dto.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));

		dto.getEndereco().getCidade().add(algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));

		dto.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(), "formas-pagamento"));

		dto.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(), "responsaveis"));

		return dto;
	}

	@Override
	public CollectionModel<RestauranteDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {

		return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
	}

}