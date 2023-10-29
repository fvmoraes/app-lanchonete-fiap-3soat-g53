package com.fiap.lanchonete.infraestrutura.repositorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fiap.lanchonete.dominio.Cliente;
import com.fiap.lanchonete.dominio.portas.repositorios.ClienteRepositoryPort;
import com.fiap.lanchonete.infraestrutura.entidades.ClienteEntity;

@Component
public class ClienteRepository implements ClienteRepositoryPort {

	SpringClienteRepository repository;

	public ClienteRepository(SpringClienteRepository repository) {
		this.repository = repository;
	}
	
	
	@Override
	public List<Cliente> buscarTodos() {
		
		List<ClienteEntity> clientes = repository.findAll();

		
		return clientes.stream()
			    .map(cliente -> cliente.toCliente())
			    .collect(Collectors.toList());
	}

	@Override
	public void salvar(Cliente cliente) {
		repository.save(new ClienteEntity(cliente.getCpf(), cliente.getNome()));
		
	}

	@Override
	public Cliente buscarPorCpf(String cpf) {
		var client = repository.findByCpf(cpf);
		if (client == null) {
			return null;
		}
			return client.toCliente();
	}


	@Override
	public void deleta(String cpf) {
		var clientEntity = repository.findByCpf(cpf);
		if (clientEntity != null)
			repository.delete(clientEntity);		
	}

}
