package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.v1.model.EnderecoDTO;
import com.algaworks.algafood.api.v1.model.input.ItemPedidoInput;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	ModelMapper modelMapper() {

		var modelMapper = new ModelMapper();

		// modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
		// .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class).addMappings(mapper -> mapper.skip(ItemPedido::setId));

		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);

		enderecoToEnderecoModelTypeMap.<String> addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

		return modelMapper;
	}

}