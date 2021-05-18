package com.alelo.alelofrota.domain.exception;

public class FabricanteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FabricanteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public FabricanteNaoEncontradoException(Long fabricanteId) {
		this(String.format("Não existe um cadastro de fabricante com código %d", fabricanteId));
	}
	
}
