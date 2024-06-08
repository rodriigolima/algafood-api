package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
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

	@Autowired
	private AlgaSecurity security;

	public PedidoModelAssembler() {

		super(PedidoController.class, PedidoDTO.class);
	}

	@Override
	public PedidoDTO toModel(Pedido pedido) {

		PedidoDTO dto = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, dto);

		if (this.security.podePesquisarPedidos())
			dto.add(algaLinks.linkToPedidos("pedidos"));

		if (this.security.podeGerenciarPedidos(pedido.getCodigo())) {
			if (pedido.isConfirmado())
				dto.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));

			if (pedido.isCancelado())
				dto.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));

			if (pedido.isEntregue())
				dto.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
		}

		if (this.security.podeConsultarRestaurantes())
			dto.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

		if (this.security.podeConsultarUsuariosGruposPermissoes())
			dto.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

		if (this.security.podeConsultarFormasPagamento())
			dto.getFormaPagamento().add(algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

		if (this.security.podeConsultarCidades())
			dto.getEnderecoEntrega().getCidade().add(algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

		if (this.security.podeConsultarRestaurantes())
			dto.getItens().forEach(item -> {
				item.add(algaLinks.linkToProduto(dto.getRestaurante().getId(), item.getProdutoId(), "produto"));
			});

		return dto;
	}

}