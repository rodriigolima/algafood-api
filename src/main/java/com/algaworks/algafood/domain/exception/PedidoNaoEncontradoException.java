package com.algaworks.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
    
    public static final String MSG_PEDIDO_NAO_ENCONTRADA = "Não existe um cadastro de pedido com código %d";

    public PedidoNaoEncontradoException(String mensagem) {
        super( mensagem);
    }

    public PedidoNaoEncontradoException(Long pedidoId) {
        this(String.format(MSG_PEDIDO_NAO_ENCONTRADA, pedidoId));
    }
}
