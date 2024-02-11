package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.model.PedidoResumoDTO;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public PedidoResumoModelAssembler() {

		super(PedidoController.class, PedidoResumoDTO.class);
	}

	@Override
	public PedidoResumoDTO toModel(Pedido pedido) {

		PedidoResumoDTO dto = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, dto);

		dto.add(algaLinks.linkToPedidos("pedidos"));

		dto.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

		dto.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

		return dto;
	}

}
