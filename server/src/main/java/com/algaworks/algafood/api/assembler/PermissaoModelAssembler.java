package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.PermissaoDTO;
import com.algaworks.algafood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PermissaoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PermissaoDTO toModel(Permissao permissao) {

		return modelMapper.map(permissao, PermissaoDTO.class);
	}

	public List<PermissaoDTO> toCollectionModel(Collection<Permissao> permissoes) {

		return permissoes.stream().map(this::toModel).toList();
	}
}
