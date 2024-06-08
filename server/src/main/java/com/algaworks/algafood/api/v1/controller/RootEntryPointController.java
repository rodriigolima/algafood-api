package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.core.security.AlgaSecurity;

@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity security;

	@GetMapping
	public RootEntryPointDTO root() {

		RootEntryPointDTO dto = new RootEntryPointDTO();

		if (this.security.podeConsultarCozinhas())
			dto.add(this.algaLinks.linkToCozinhas("cozinhas"));

		if (this.security.podePesquisarPedidos())
			dto.add(this.algaLinks.linkToPedidos("pedidos"));

		if (this.security.podeConsultarRestaurantes())
			dto.add(this.algaLinks.linkToRestaurantes("restaurantes"));

		if (this.security.podeConsultarUsuariosGruposPermissoes()) {
			dto.add(this.algaLinks.linkToGrupos("grupos"));
			dto.add(this.algaLinks.linkToUsuarios("usuarios"));
			dto.add(this.algaLinks.linkToPermissoes("permissoes"));
		}

		if (this.security.podeConsultarFormasPagamento())
			dto.add(this.algaLinks.linkToFormasPagamento("formas-pagamento"));

		if (this.security.podeConsultarEstados())
			dto.add(this.algaLinks.linkToEstados("estados"));

		if (this.security.podeConsultarCidades())
			dto.add(this.algaLinks.linkToCidades("cidades"));

		if (this.security.podeConsultarEstatisticas())
			dto.add(this.algaLinks.linkToEstatisticas("estatisticas"));

		return dto;
	}

	private static class RootEntryPointDTO extends RepresentationModel<RootEntryPointDTO> {

	}

}
