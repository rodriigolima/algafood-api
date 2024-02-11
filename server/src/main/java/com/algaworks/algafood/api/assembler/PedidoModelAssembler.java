package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.model.PedidoDTO;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public PedidoModelAssembler() {

		super(PedidoController.class, PedidoDTO.class);
	}

	@Override
	public PedidoDTO toModel(Pedido pedido) {

		PedidoDTO dto = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, dto);

		dto.add(algaLinks.linkToPedidos("pedidos"));

		if (pedido.isConfirmado())
			dto.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));

		if (pedido.isCancelado())
			dto.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));

		if (pedido.isEntregue())
			dto.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));

		dto.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

		dto.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

		dto.getFormaPagamento().add(algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

		dto.getEnderecoEntrega().getCidade().add(algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

		dto.getItens().forEach(item -> {
			item.add(algaLinks.linkToProduto(dto.getRestaurante().getId(), item.getProdutoId(), "produto"));
		});

		return dto;
	}

}