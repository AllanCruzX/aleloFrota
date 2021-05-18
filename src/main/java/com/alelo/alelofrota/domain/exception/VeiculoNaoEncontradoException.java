package com.alelo.alelofrota.domain.exception;

public class VeiculoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public VeiculoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public VeiculoNaoEncontradoException(Long modeloId) {
		this(String.format("Não existe um cadastro de veículo com código %d", modeloId));
	}
	
}
