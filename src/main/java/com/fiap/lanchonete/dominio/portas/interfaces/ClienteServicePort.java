package com.fiap.lanchonete.dominio.portas.interfaces;

import java.util.List;

import com.fiap.lanchonete.dominio.dtos.ClienteDto;
import com.fiap.lanchonete.dominio.exceptions.ClientJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ClientNaoEncontradoException;

public interface ClienteServicePort {


	List<ClienteDto> buscarClientes();
	void CriaCliente(ClienteDto dto) throws ClientJaCadastradoException;
	void AtualizaCliente(ClienteDto dto) throws ClientNaoEncontradoException;
	ClienteDto buscaClienteCpf(String cpf);
	void DeletaCliente(ClienteDto dto);
	void DeletaCliente(String cpf);
		
}
