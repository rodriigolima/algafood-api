package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.model.FormaPagamentoDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public FormaPagamentoModelAssembler() {

		super(FormaPagamentoController.class, FormaPagamentoDTO.class);
	}

	@Override
	public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {

		FormaPagamentoDTO FormaPagamentoDTO = createModelWithId(formaPagamento.getId(), formaPagamento);

		modelMapper.map(formaPagamento, FormaPagamentoDTO);

		FormaPagamentoDTO.add(algaLinks.linkToFormasPagamento("formasPagamento"));

		return FormaPagamentoDTO;
	}

	@Override
	public CollectionModel<FormaPagamentoDTO> toCollectionModel(Iterable<? extends FormaPagamento> entities) {

		return super.toCollectionModel(entities).add(algaLinks.linkToFormasPagamento());
	}
}
