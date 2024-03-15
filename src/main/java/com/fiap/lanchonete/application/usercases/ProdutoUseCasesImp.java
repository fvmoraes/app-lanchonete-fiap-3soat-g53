package com.fiap.lanchonete.application.usercases;

import java.util.List;

import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usercases.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;

public class ProdutoUseCasesImp implements ProdutoUseCases{

	private final ProdutoGateway produtoGateway;

	public ProdutoUseCasesImp(ProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}
	
	@Override
	public List<Produto> buscarProdutos() {
		return produtoGateway.buscarTodos();
	}

	@Override
	public List<Produto> buscarProdutosCategoria(Categoria categoria) {
		return produtoGateway.buscarPorCategoria(categoria);
	}
	
	@Override
	public void cadastraProduto(Produto produto) throws ProdutoJaCadastradoException {
		
		if (produtoGateway.buscarPeloNome(produto.getNome()) != null)
			throw new ProdutoJaCadastradoException();

		produtoGateway.salvar(produto);
	}

	@Override
	public void atualizaProduto(Produto produto) throws ProdutoNaoEncontradoException {
		
		if (produtoGateway.buscarPeloNome(produto.getNome()) == null)
			throw new ProdutoNaoEncontradoException();
		produtoGateway.salvar(produto);
	}

	@Override
	public Produto buscaProdutoNome(String nome) throws ProdutoNaoEncontradoException {
		Produto produto = produtoGateway.buscarPeloNome(nome);
			if (produto == null)
					throw new ProdutoNaoEncontradoException();
			return produto;
	}

	@Override
	public void deletaProduto(String nome) {
		produtoGateway.deletaProduto(nome);

	}

}
