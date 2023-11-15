package com.algaworks.algafood.api.model;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@JsonView(RestauranteView.Resumo.class)
@Setter@Getter
public class CozinhaDTO {
    
    private Long id;
    
    private String nome;
}
