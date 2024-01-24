package com.fiap.lanchonete.application.usercases;

import java.util.List;

import com.fiap.lanchonete.application.gateways.ClienteGateway;
import com.fiap.lanchonete.application.usercases.exceptions.ClientJaCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.ClientNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Cliente;

public class ClienteUseCasesImp implements ClienteUseCases{

	private final ClienteGateway clienteGateway;

	
	public ClienteUseCasesImp(ClienteGateway clienteGateway) {
		this.clienteGateway = clienteGateway;
	}

	@Override
	public List<Cliente> buscarClientes() {
		return clienteGateway.buscaClientes();

	}
	
	@Override
	public Cliente criaCliente(Cliente cliente) throws ClientJaCadastradoException {
		if (clienteGateway.buscaClientes(cliente.cpf()) != null) {
			throw new ClientJaCadastradoException();
		}
		
		return clienteGateway.criaCliente(cliente);
	}
	
	@Override
	public Cliente buscaClienteCpf(String cpf) {
		return clienteGateway.buscaClientes(cpf);

		}

	@Override
	public String deletaCliente(Cliente cliente) {
		return clienteGateway.deletaCliente(cliente);

	}

	@Override
	public String deletaCliente(String cpf) {
		return clienteGateway.deletaCliente(cpf);

	}

	@Override
	public void atualizaCliente(Cliente cliente) throws ClientNaoEncontradoException {
		if (clienteGateway.buscaClientes(cliente.cpf()) == null)
			throw new ClientNaoEncontradoException();
		clienteGateway.atualiziaCliente(cliente);
		
	}

	
}
