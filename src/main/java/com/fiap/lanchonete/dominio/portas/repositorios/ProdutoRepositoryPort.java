package com.fiap.lanchonete.dominio.portas.repositorios;

import java.util.List;

import com.fiap.lanchonete.dominio.Categoria;
import com.fiap.lanchonete.dominio.Produto;

public interface ProdutoRepositoryPort {
	List<Produto> buscarTodos();
	
	Produto buscarPeloNome(String nome);
	
	void salvar(Produto produto);
	
	List<Produto> buscarPorCategoria(Categoria categoria);
	
	void deleta(String produto);
 }
