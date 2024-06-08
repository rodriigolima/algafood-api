package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity security;

	public RestauranteModelAssembler() {

		super(RestauranteController.class, RestauranteDTO.class);
	}

	@Override
	public RestauranteDTO toModel(Restaurante restaurante) {

		RestauranteDTO dto = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, dto);

		if (this.security.podeConsultarRestaurantes())
			dto.add(algaLinks.linkToRestaurantes("restaurantes"));

		if (this.security.podeGerenciarCadastroRestaurantes()) {
			if (restaurante.ativacaoPermitida())
				dto.add(algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));

			if (restaurante.inativacaoPermitida())
				dto.add(algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
		}

		if (this.security.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
			if (restaurante.aberturaPermitida())
				dto.add(algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));

			if (restaurante.fechamentoPermitido())
				dto.add(algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
		}

		if (this.security.podeConsultarRestaurantes())
			dto.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));

		if (this.security.podeConsultarCozinhas())
			dto.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));

		if (this.security.podeConsultarCidades())
			if (dto.getEndereco() != null && dto.getEndereco().getCidade() != null)
				dto.getEndereco().getCidade().add(algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));

		if (this.security.podeConsultarRestaurantes())
			dto.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(), "formas-pagamento"));

		if (this.security.podeGerenciarCadastroRestaurantes())
			dto.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(), "responsaveis"));

		return dto;
	}

	@Override
	public CollectionModel<RestauranteDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {

		CollectionModel<RestauranteDTO> dto = super.toCollectionModel(entities);
		if (this.security.podeConsultarRestaurantes())
			dto.add(algaLinks.linkToRestaurantes());
		return dto;
	}

}
