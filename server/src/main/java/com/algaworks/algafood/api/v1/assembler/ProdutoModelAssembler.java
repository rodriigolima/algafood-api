package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.v1.model.ProdutoDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;

	public ProdutoModelAssembler() {

		super(RestauranteProdutoController.class, ProdutoDTO.class);
	}

	@Override
	public ProdutoDTO toModel(Produto produto) {

		ProdutoDTO dto = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());

		modelMapper.map(produto, dto);
		
		if (algaSecurity.podeConsultarRestaurantes()) { 
			dto.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

			dto.add(algaLinks.linkToFotoProduto(produto.getRestaurante().getId(), produto.getId(), "foto"));
		}

		return dto;
	}
}
