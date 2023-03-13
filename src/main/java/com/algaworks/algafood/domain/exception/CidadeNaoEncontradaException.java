package com.algaworks.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    public static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com código %d";
    
    public CidadeNaoEncontradaException(String mensagem) {
        super( mensagem);
    }
    
    public CidadeNaoEncontradaException(Long cidadeId) {
        this(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId) );
    }
}
