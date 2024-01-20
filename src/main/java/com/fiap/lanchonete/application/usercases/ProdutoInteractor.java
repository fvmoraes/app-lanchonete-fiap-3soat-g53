package com.fiap.lanchonete.application.usercases;

import java.util.List;

import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usercases.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;

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
		
		if (produtoGateway.buscarPeloNome(produto.nome()) != null)
			throw new ProdutoJaCadastradoException();

		produtoGateway.salvar(produto);
	}

	
	public void atualizaProduto(Produto produto) throws ProdutoNaoEncontradoException {
		
		if (produtoGateway.buscarPeloNome(produto.nome()) == null)
			throw new ProdutoNaoEncontradoException();
		produtoGateway.salvar(produto);
	}

	
	public Produto buscaProdutoNome(String nome) throws ProdutoNaoEncontradoException {
		Produto produto = produtoGateway.buscarPeloNome(nome);
			if (produto == null)
					throw new ProdutoNaoEncontradoException();
			return produto;
	}

	public void deletaProduto(String nome) {
		produtoGateway.deletaProduto(nome);

	}

}
