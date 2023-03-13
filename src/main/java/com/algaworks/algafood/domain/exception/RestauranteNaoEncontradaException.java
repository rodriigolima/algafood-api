package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {
    
    public static final String MSG_RESTAURANTE_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";

    public RestauranteNaoEncontradaException(String mensagem) {
        super( mensagem);
    }

    public RestauranteNaoEncontradaException(Long cozinhaId) {
        this(String.format(MSG_RESTAURANTE_NAO_ENCONTRADA, cozinhaId));
    }
}
