package com.algaworks.algafood.domain.exception;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe cadastro de estado com código %d";
    
    public EstadoNaoEncontradaException(String mensagem) {
        super( mensagem);
    }
    
    public EstadoNaoEncontradaException(Long estadoId) {
        this(String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId) );
    }
}
