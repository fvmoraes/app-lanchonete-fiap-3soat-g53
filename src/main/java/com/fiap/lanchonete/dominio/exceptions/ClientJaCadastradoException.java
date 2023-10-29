package com.fiap.lanchonete.dominio.exceptions;

public class ClientJaCadastradoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ClientJaCadastradoException() {
        super("Cliente já existe no banco de dados");
    }

}
