package com.algaworks.algafood.api.mixin;

import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class CozinhaMixin {
    
    @JsonIgnore
    private List<Restaurante> restaurantes;
}
