package com.fiap.lanchonete.dominio.adaptadores.servicos;

import java.util.List;
import java.util.stream.Collectors;

import com.fiap.lanchonete.dominio.Cliente;
import com.fiap.lanchonete.dominio.dtos.ClienteDto;
import com.fiap.lanchonete.dominio.exceptions.ClientJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ClientNaoEncontradoException;
import com.fiap.lanchonete.dominio.portas.interfaces.ClienteServicePort;
import com.fiap.lanchonete.dominio.portas.repositorios.ClienteRepositoryPort;

public class ClienteServiceImpl  implements ClienteServicePort {

	ClienteRepositoryPort repository;

	public ClienteServiceImpl(ClienteRepositoryPort repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<ClienteDto> buscarClientes() {
		return repository.buscarTodos().stream()
			    .map(cliente -> new ClienteDto(cliente.getCpf(),cliente.getNome()))
			    .collect(Collectors.toList());
	}

	@Override
	public void CriaCliente(ClienteDto dto) throws ClientJaCadastradoException {
		if (buscaClienteCpf(dto.getCpf()) != null)
			throw new ClientJaCadastradoException();
		repository.salvar(dto.toClient());
		
	}

	@Override
	public void AtualizaCliente(ClienteDto dto) throws ClientNaoEncontradoException {
		var atualizar = repository.buscarPorCpf(dto.getCpf().replaceAll("[^0-9]", ""));
		if (atualizar == null)
			throw new ClientNaoEncontradoException();
		atualizar.setNome(dto.getNome());
		repository.salvar(atualizar);
	}

	@Override
	public ClienteDto buscaClienteCpf(String cpf) {
		 Cliente cliente =  repository.buscarPorCpf(cpf.replaceAll("[^0-9]", ""));
		 if (cliente == null) {
			 return null;
		 }
		 
		 return new ClienteDto(cliente.getCpf(), cliente.getNome());
	}

	@Override
	public void DeletaCliente(ClienteDto dto) {
		 repository.deleta(dto.getCpf());

	}

	@Override
	public void DeletaCliente(String cpf) {
		 repository.deleta(cpf);
	}

	
}
