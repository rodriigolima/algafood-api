package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.model.CozinhaDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity security;

	public CozinhaModelAssembler() {

		super(CozinhaController.class, CozinhaDTO.class);
	}

	@Override
	public CozinhaDTO toModel(Cozinha cozinha) {

		CozinhaDTO dto = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, dto);

		if (this.security.podeConsultarCozinhas())
			dto.add(algaLinks.linkToCozinhas("cozinhas"));

		return dto;
	}

}
