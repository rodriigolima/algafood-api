package com.algaworks.algafood.api.model.input;

import com.algaworks.algafood.core.validation.TaxaFrete;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter
public class RestauranteInput {
    @NotBlank
    private String nome;

    @TaxaFrete
    @NotNull
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdInput cozinha;
}
