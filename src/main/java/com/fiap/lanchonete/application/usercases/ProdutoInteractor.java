package com.fiap.lanchonete.application.usercases;

import java.util.List;

import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.dominio.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ProdutoNaoEncontradoException;

public class ProdutoInteractor {

	private final ProdutoGateway produtoGateway;

	public ProdutoInteractor(ProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}

	public List<Produto> buscarProdutos() {
		return produtoGateway.buscarTodos();
	}

	public List<Produto> buscarProdutosCategoria(Categoria categoria) {
		return produtoGateway.buscarPorCategoria(categoria);
	}

	public void cadastraProduto(Produto produto) throws ProdutoJaCadastradoException {
		produtoGateway.salvar(produto);
	}

	
	public void atualizaProduto(Produto produto) throws ProdutoNaoEncontradoException {
		produtoGateway.salvar(produto);
	}

	
	public Produto buscaProdutoNome(String nome) throws ProdutoNaoEncontradoException {
		return produtoGateway.buscarPeloNome(nome);
	}

	public void deletaProduto(String nome) {
		produtoGateway.deletaProduto(nome);

	}

}
