package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.AlgaLinks;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {
	
	@Autowired
	private AlgaLinks algaLinks;

	@GetMapping
	public RootEntryPointDTO root() {

		RootEntryPointDTO dto = new RootEntryPointDTO();
		
		dto.add(algaLinks.linkToCozinhas("cozinhas"));
		dto.add(algaLinks.linkToPedidos("pedidos"));
		dto.add(algaLinks.linkToRestaurantes("restaurantes"));
		dto.add(algaLinks.linkToGrupos("grupos"));
		dto.add(algaLinks.linkToUsuarios("usuarios"));
		dto.add(algaLinks.linkToPermissoes("permissoes"));
		dto.add(algaLinks.linkToFormasPagamento("formas-pagamento"));
		dto.add(algaLinks.linkToEstados("estados"));
		dto.add(algaLinks.linkToCidades("cidades"));
		dto.add(algaLinks.linkToEstatisticas("estatisticas"));

		return dto;
	}

	private static class RootEntryPointDTO extends RepresentationModel<RootEntryPointDTO> {

	}

}
