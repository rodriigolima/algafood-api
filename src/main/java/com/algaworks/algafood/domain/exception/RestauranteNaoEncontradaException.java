package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {
    
    public static final String MSG_RESTAURANTE_NAO_ENCONTRADA = "Não existe um cadastro de restaurante com código %d";

    public RestauranteNaoEncontradaException(String mensagem) {
        super( mensagem);
    }

    public RestauranteNaoEncontradaException(Long restauranteId) {
        this(String.format(MSG_RESTAURANTE_NAO_ENCONTRADA, restauranteId));
    }
}
