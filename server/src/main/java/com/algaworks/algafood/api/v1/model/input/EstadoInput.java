package com.algaworks.algafood.api.v1.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoInput {

	@NotBlank
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private String nome;
	
}
