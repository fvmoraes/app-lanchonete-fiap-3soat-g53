package com.fiap.lanchonete.application.usercases;

import java.util.List;

import com.fiap.lanchonete.application.gateways.ClienteGateway;
import com.fiap.lanchonete.domain.entity.Cliente;
import com.fiap.lanchonete.dominio.exceptions.ClientJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ClientNaoEncontradoException;

public class ClienteInteractor {

	private final ClienteGateway clienteGateway;

	
	public ClienteInteractor(ClienteGateway clienteGateway) {
		this.clienteGateway = clienteGateway;
	}


	public List<Cliente> buscarClientes() {
		return clienteGateway.buscaClientes();

	}

	
	public Cliente criaCliente(Cliente cliente) throws ClientJaCadastradoException {
		return clienteGateway.criaCliente(cliente);
	}

	/*
	public Cliente AtualizaCliente(ClienteDto dto) throws ClientNaoEncontradoException {
		return clienteGateway.atualizaCliente();

	}
*/
	
	public Cliente buscaClienteCpf(String cpf) {
		return clienteGateway.buscaClientes(cpf);

		}

	
	public String deletaCliente(Cliente cliente) {
		return clienteGateway.deletaCliente(cliente);

	}

	
	public String deletaCliente(String cpf) {
		return clienteGateway.deletaCliente(cpf);

	}


	public void AtualizaCliente(Cliente cliente) throws ClientNaoEncontradoException {
		clienteGateway.atualiziaCliente(cliente);
		
	}

	
}
