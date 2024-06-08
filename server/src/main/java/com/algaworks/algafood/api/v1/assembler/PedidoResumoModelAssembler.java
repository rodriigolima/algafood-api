package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoResumoDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity security;  

	public PedidoResumoModelAssembler() {

		super(PedidoController.class, PedidoResumoDTO.class);
	}

	@Override
	public PedidoResumoDTO toModel(Pedido pedido) {

		PedidoResumoDTO dto = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, dto);

		if (this.security.podePesquisarPedidos())
			dto.add(algaLinks.linkToPedidos("pedidos"));

		if (this.security.podeConsultarRestaurantes())
			dto.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
		
		if (this.security.podeConsultarUsuariosGruposPermissoes())
			dto.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

		return dto;
	}

}
