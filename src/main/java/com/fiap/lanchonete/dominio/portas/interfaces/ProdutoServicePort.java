package com.fiap.lanchonete.dominio.portas.interfaces;

import java.util.List;

import com.fiap.lanchonete.dominio.Categoria;
import com.fiap.lanchonete.dominio.dtos.ProdutoDto;
import com.fiap.lanchonete.dominio.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ProdutoNaoEncontradoException;

public interface ProdutoServicePort {
	
	List<ProdutoDto> buscarProdutos();
	void cadastraProduto(ProdutoDto dto) throws ProdutoJaCadastradoException;
	void atualizaProduto(ProdutoDto dto) throws ProdutoNaoEncontradoException;
	ProdutoDto buscaProdutoNome(String nome) throws ProdutoNaoEncontradoException;
	void deletaProduto(String nome);
	List<ProdutoDto> buscarProdutosCategoria(Categoria categoria);
}
