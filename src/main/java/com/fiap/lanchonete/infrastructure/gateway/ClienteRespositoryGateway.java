package com.fiap.lanchonete.infrastructure.gateway;

import java.util.List;
import java.util.stream.Collectors;

import com.fiap.lanchonete.application.gateways.ClienteGateway;
import com.fiap.lanchonete.domain.entity.Cliente;
import com.fiap.lanchonete.dominio.exceptions.ClientNaoEncontradoException;
import com.fiap.lanchonete.infrastructure.persistence.ClienteEntity;
import com.fiap.lanchonete.infrastructure.persistence.ClienteRepository;

public class ClienteRespositoryGateway implements ClienteGateway {

	private final ClienteRepository clienteRepository;
	private final ClienteEntityMapper clienteMapper;

	public ClienteRespositoryGateway(ClienteRepository clienteRepository, ClienteEntityMapper clienteMapper) {
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
	}

	@Override
	public Cliente criaCliente(Cliente clienteDomainObj) {
		ClienteEntity clienteEntity = clienteMapper.paraEntity(clienteDomainObj);
		ClienteEntity clienteSalvo = clienteRepository.save(clienteEntity);
		return clienteMapper.paraObject(clienteSalvo);
	}

	@Override
	public List<Cliente> buscaClientes() {
		List<ClienteEntity> clientsEntity = clienteRepository.findAll();
		return clientsEntity.stream().map(cliente -> clienteMapper.paraObject(cliente)).collect(Collectors.toList());
	}


	@Override
	public Cliente buscaClientes(String cpf) {
		ClienteEntity clientsEntity = clienteRepository.findByCpf(cpf);
		return clienteMapper.paraObject(clientsEntity);

	}

	@Override
	public String deletaCliente(Cliente cliente) {
		ClienteEntity clientsEntity = clienteMapper.paraEntity(cliente); 
		clienteRepository.delete(clientsEntity);
		return null;
	}

	@Override
	public String deletaCliente(String cpf) {
		ClienteEntity clientEntity = clienteRepository.findByCpf(cpf);
		if (clientEntity != null)
			clienteRepository.delete(clientEntity);		
	
		return null;
	}

	@Override
	public Cliente atualiziaCliente(Cliente clienteDomainObj) throws ClientNaoEncontradoException {
		ClienteEntity clientEntity = clienteRepository.findByCpf(clienteDomainObj.cpf());
		if (clientEntity == null) {
			throw new ClientNaoEncontradoException();		
		}
			ClienteEntity clienteEntity = clienteMapper.paraEntity(clienteDomainObj);
			ClienteEntity clienteSalvo = clienteRepository.save(clienteEntity);
			return clienteMapper.paraObject(clienteSalvo);		
	}
	
	 
}
