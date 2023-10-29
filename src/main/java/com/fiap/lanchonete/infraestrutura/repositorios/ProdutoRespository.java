package com.fiap.lanchonete.infraestrutura.repositorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fiap.lanchonete.dominio.Categoria;
import com.fiap.lanchonete.dominio.Produto;
import com.fiap.lanchonete.dominio.portas.repositorios.ProdutoRepositoryPort;
import com.fiap.lanchonete.infraestrutura.entidades.ProdutoEntity;

@Component
public class ProdutoRespository implements ProdutoRepositoryPort {

	SpringProdutoRepository repository;

	public ProdutoRespository(SpringProdutoRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Produto> buscarTodos() {
		
		List<ProdutoEntity> produtos = repository.findAll();
		
		return produtos.stream()
			    .map(produto -> produto.toProduto())
			    .collect(Collectors.toList());
			}

	@Override
	public Produto buscarPeloNome(String nome) {
		ProdutoEntity produtoEntity = repository.findByNome(nome);
		return produtoEntity != null? produtoEntity.toProduto() : null;
	}

	@Override
	public void salvar(Produto produto) {
		
		repository.save(new ProdutoEntity(produto.getCategoria(),produto.getNome(),produto.getDescricao(),produto.getValor()));
		
	}

	@Override
	public List<Produto> buscarPorCategoria(Categoria categoria) {
		var produtos =  repository.findByCategoria(categoria);
		
		return produtos != null ? produtos.stream()
			    .map(produto -> produto.toProduto())
			    .collect(Collectors.toList())
				 : null;
	}

	@Override
	public void deleta(String nome) {
		var a = repository.findByNome(nome);
		repository.delete(a);
		
	}

}
