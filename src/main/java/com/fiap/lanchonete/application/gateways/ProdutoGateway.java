package com.fiap.lanchonete.application.gateways;

import java.util.List;

import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;



public interface ProdutoGateway {
	
	List<Produto> buscarTodos();
	
	Produto buscarPeloNome(String nome);
	
	Produto salvar(Produto produto);
	
	List<Produto> buscarPorCategoria(Categoria categoria);
	
	void deletaProduto(String produto);
	
}
