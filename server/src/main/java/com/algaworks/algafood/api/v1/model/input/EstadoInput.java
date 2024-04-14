package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoInput {

	@NotBlank
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private String nome;

}
