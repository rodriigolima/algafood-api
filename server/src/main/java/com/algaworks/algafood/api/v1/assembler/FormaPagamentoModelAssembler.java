package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.FormaPagamentoController;
import com.algaworks.algafood.api.v1.model.FormaPagamentoDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity security;

	public FormaPagamentoModelAssembler() {

		super(FormaPagamentoController.class, FormaPagamentoDTO.class);
	}

	@Override
	public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {

		FormaPagamentoDTO dto = createModelWithId(formaPagamento.getId(), formaPagamento);

		modelMapper.map(formaPagamento, dto);

		if (this.security.podeConsultarFormasPagamento())
			dto.add(algaLinks.linkToFormasPagamento("formasPagamento"));

		return dto;
	}

	@Override
	public CollectionModel<FormaPagamentoDTO> toCollectionModel(Iterable<? extends FormaPagamento> entities) {

		CollectionModel<FormaPagamentoDTO> dto = super.toCollectionModel(entities);

		if (this.security.podeConsultarFormasPagamento())
			dto.add(algaLinks.linkToFormasPagamento());

		return dto;
	}
}
