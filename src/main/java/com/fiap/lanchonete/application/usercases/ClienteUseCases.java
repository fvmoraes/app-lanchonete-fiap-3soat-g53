package com.fiap.lanchonete.application.usercases;

import java.util.List;

import com.fiap.lanchonete.application.usercases.exceptions.ClientJaCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.ClientNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Cliente;

public interface ClienteUseCases {

	public List<Cliente> buscarClientes();	
	public Cliente criaCliente(Cliente cliente) throws ClientJaCadastradoException;
		
	public Cliente buscaClienteCpf(String cpf);
	
	public String deletaCliente(Cliente cliente);
	
	public String deletaCliente(String cpf);

	public void atualizaCliente(Cliente cliente) throws ClientNaoEncontradoException;


}
