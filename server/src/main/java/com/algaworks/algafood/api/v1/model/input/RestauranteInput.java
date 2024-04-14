package com.algaworks.algafood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.core.validation.TaxaFrete;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteInput {

	@NotBlank
	private String nome;

	@TaxaFrete
	@NotNull
	private BigDecimal taxaFrete;

	@Valid
	@NotNull
	private CozinhaIdInput cozinha;

	@Valid
	@NotNull
	private EnderecoInput endereco;

}
