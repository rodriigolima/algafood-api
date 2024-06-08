package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.CidadeController;
import com.algaworks.algafood.api.v1.model.CidadeDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity security;

	public CidadeModelAssembler() {

		super(CidadeController.class, CidadeDTO.class);
	}

	@Override
	public CidadeDTO toModel(Cidade cidade) {

		CidadeDTO dto = createModelWithId(cidade.getId(), cidade);

		modelMapper.map(cidade, dto);

		if (this.security.podeConsultarCidades())
			dto.add(algaLinks.linkToCidades("cidades"));

		if (this.security.podeConsultarEstados())
			dto.getEstado().add(algaLinks.linkToEstado(dto.getEstado().getId()));

		return dto;
	}

	@Override
	public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> entities) {

		CollectionModel<CidadeDTO> dto = super.toCollectionModel(entities);

		if (this.security.podeConsultarCidades())
			dto.add(algaLinks.linkToCidades());

		return dto;
	}

}
