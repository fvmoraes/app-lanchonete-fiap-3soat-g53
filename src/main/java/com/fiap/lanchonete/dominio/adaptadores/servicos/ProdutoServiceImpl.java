package com.fiap.lanchonete.dominio.adaptadores.servicos;

import java.util.List;
import java.util.stream.Collectors;

import com.fiap.lanchonete.dominio.Categoria;
import com.fiap.lanchonete.dominio.Produto;
import com.fiap.lanchonete.dominio.dtos.ProdutoDto;
import com.fiap.lanchonete.dominio.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.dominio.portas.interfaces.ProdutoServicePort;
import com.fiap.lanchonete.dominio.portas.repositorios.ProdutoRepositoryPort;

public class ProdutoServiceImpl implements ProdutoServicePort {

	ProdutoRepositoryPort repository;

	public ProdutoServiceImpl(ProdutoRepositoryPort repository) {
		this.repository = repository;
	}

	@Override
	public List<ProdutoDto> buscarProdutos() {
		List<Produto> produtos = repository.buscarTodos();
		List<ProdutoDto> produtosDto = produtos.stream().map(produto -> new ProdutoDto(produto.getCategoria(),
				produto.getNome(), produto.getDescricao(), produto.getValor())).collect(Collectors.toList());
		return produtosDto;
	}

	@Override
	public List<ProdutoDto> buscarProdutosCategoria(Categoria categoria) {
		List<Produto> produtos = repository.buscarPorCategoria(categoria);
		List<ProdutoDto> produtosDto = produtos.stream().map(produto -> new ProdutoDto(produto.getCategoria(),
				produto.getNome(), produto.getDescricao(), produto.getValor())).collect(Collectors.toList());
		return produtosDto;
	}

	@Override
	public void cadastraProduto(ProdutoDto dto) throws ProdutoJaCadastradoException {
		try {
			if (buscaProdutoNome(dto.getNome()) != null)
				throw new ProdutoJaCadastradoException();
		} catch (ProdutoNaoEncontradoException e) {
		}
		repository.salvar(new Produto(dto));
	}

	@Override
	public void atualizaProduto(ProdutoDto dto) throws ProdutoNaoEncontradoException {
		Produto produto = repository.buscarPeloNome(dto.getNome());
		if (produto == null)
			throw new ProdutoNaoEncontradoException();

		produto.setCategoria(dto.getCategoria());
		produto.setDescricao(dto.getDescricao());
		produto.setValor(dto.getValor());
		repository.salvar(produto);
	}

	@Override
	public ProdutoDto buscaProdutoNome(String nome) throws ProdutoNaoEncontradoException {
		Produto produto = repository.buscarPeloNome(nome);
		if (produto == null) {
			throw new ProdutoNaoEncontradoException();
		}
		return new ProdutoDto(produto.getCategoria(), produto.getNome(), produto.getDescricao(), produto.getValor());
	}

	@Override
	public void deletaProduto(String nome) {
		repository.deleta(nome);

	}

}
