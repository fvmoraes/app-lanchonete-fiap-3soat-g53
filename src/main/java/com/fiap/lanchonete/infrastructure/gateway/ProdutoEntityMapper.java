package com.fiap.lanchonete.infrastructure.gateway;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.infrastructure.persistence.ProdutoEntity;

public class ProdutoEntityMapper {

	ProdutoEntity paraProdutoEntity(Produto produto) {
	return new ProdutoEntity(produto.categoria(),produto.nome(),produto.descricao(),produto.valor());

	}
	
	Produto paraObjetoDominio(ProdutoEntity produtoEntity) {
		return new Produto(produtoEntity.getCategoria(),produtoEntity.getNome(),produtoEntity.getDescricao(),produtoEntity.getValor());

	}
}
