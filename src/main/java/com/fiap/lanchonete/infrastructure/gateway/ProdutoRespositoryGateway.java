package com.fiap.lanchonete.infrastructure.gateway;

import java.util.List;

import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.infrastructure.gateway.mapper.ProdutoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.ProdutoRepository;
import com.fiap.lanchonete.infrastructure.persistence.entity.ProdutoEntity;

public class ProdutoRespositoryGateway implements ProdutoGateway {

	private final ProdutoRepository repository;
	private final ProdutoEntityMapper mapper;
	
	public ProdutoRespositoryGateway(ProdutoRepository repository,ProdutoEntityMapper mapper) {
		this.repository = repository;
		this.mapper= mapper;
	}

	@Override
	public List<Produto> buscarTodos() {
		
		List<ProdutoEntity> produtos = repository.findAll();
		
		return produtos.stream()
			    .map(mapper::paraObjetoDominio).toList();
			}

	@Override
	public Produto buscarPeloNome(String nome) {
		ProdutoEntity produtoEntity = repository.findByNome(nome);
		return produtoEntity != null? mapper.paraObjetoDominio(produtoEntity) : null;
	}

	@Override
	public Produto salvar(Produto produto) {
		
		ProdutoEntity produtoEntity = mapper.paraProdutoEntity(produto);
		ProdutoEntity produtoSalvo = repository.save(produtoEntity);
		return mapper.paraObjetoDominio(produtoSalvo);
		
	}

	@Override
	public List<Produto> buscarPorCategoria(Categoria categoria) {
		var produtos =  repository.findByCategoria(categoria);
		
		return produtos != null ? produtos.stream()
			    .map(mapper::paraObjetoDominio).toList()
				 : null;
	}

	@Override
	public void deletaProduto(String nome) {
		ProdutoEntity produto = repository.findByNome(nome);
		repository.delete(produto);		
	}

}
