package com.fiap.lanchonete.dominio.portas.repositorios;

import java.util.List;

import com.fiap.lanchonete.dominio.Cliente;

public interface ClienteRepositoryPort {

	List<Cliente> buscarTodos();
	
	void salvar(Cliente cliente);
	
	Cliente buscarPorCpf(String cpf);

	void deleta(String cpf);
 
}
