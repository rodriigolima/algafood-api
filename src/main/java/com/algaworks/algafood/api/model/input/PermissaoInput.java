package com.algaworks.algafood.api.model.input;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PermissaoInput {

    private String nome;

    private String descricao;
}
