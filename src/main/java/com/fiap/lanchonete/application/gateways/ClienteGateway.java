package com.fiap.lanchonete.application.gateways;

import java.util.List;

import com.fiap.lanchonete.domain.entity.Cliente;
import com.fiap.lanchonete.dominio.exceptions.ClientNaoEncontradoException;

public interface ClienteGateway { 
	
	Cliente criaCliente(Cliente cliente);

	List<Cliente> buscaClientes();

	Cliente buscaClientes(String cpf);

	String deletaCliente(Cliente cliente);

	String deletaCliente(String cpf);

	Cliente atualiziaCliente(Cliente cliente) throws ClientNaoEncontradoException;

}
