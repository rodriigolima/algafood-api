package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.GrupoDTO;
import com.algaworks.algafood.api.model.RestauranteDTO;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrupoModelAssembler {
    
    @Autowired
    private ModelMapper modelMapper;
    
    public GrupoDTO toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoDTO.class);
    }

    public List<GrupoDTO> toCollectionModel(List<Grupo> grupos) {
        return grupos.stream()
                .map(this::toModel)
                .toList();
    }
}
