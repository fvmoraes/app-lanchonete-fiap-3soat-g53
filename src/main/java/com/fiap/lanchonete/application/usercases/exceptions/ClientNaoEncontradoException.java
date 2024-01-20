package com.fiap.lanchonete.application.usercases.exceptions;

public class ClientNaoEncontradoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ClientNaoEncontradoException() {
        super("Cliente não existe no banco de dados");
    }

}
