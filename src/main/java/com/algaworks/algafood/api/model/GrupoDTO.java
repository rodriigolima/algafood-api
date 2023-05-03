package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.Permissao;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class GrupoDTO {

    private Long id;
    private String nome;
    //private Permissao permissoes;
}
