package com.fiap.lanchonete.infrastructure.gateway.mapper;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.infrastructure.persistence.entity.ProdutoEntity;

public class ProdutoEntityMapper {

	public ProdutoEntity paraProdutoEntity(Produto produto) {
	return new ProdutoEntity(produto.getCategoria(),produto.getNome(),produto.getDescricao(),produto.getValor());

	}
	
	public Produto paraObjetoDominio(ProdutoEntity produtoEntity) {
		return new Produto(produtoEntity.getCategoria(),produtoEntity.getNome(),produtoEntity.getDescricao(),produtoEntity.getValor());

	}
}
