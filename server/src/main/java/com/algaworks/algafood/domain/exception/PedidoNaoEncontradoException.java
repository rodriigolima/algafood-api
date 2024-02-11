package com.algaworks.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MSG_PEDIDO_NAO_ENCONTRADA = "Não existe um cadastro de pedido com código %s";
    
    public PedidoNaoEncontradoException(String codigoPedido) {
        super(String.format(MSG_PEDIDO_NAO_ENCONTRADA, codigoPedido));
    }
}
