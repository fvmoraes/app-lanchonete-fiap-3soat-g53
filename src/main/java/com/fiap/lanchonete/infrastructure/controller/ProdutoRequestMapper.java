package com.fiap.lanchonete.infrastructure.controller;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.ProdutoResponse;

public class ProdutoRequestMapper {
	
	Produto paraObjetoDominio(ProdutoRequest produtoRequest) {
		return new Produto(produtoRequest.categoria(), produtoRequest.nome(), produtoRequest.descricao(), produtoRequest.valor());

	}
	
	ProdutoResponse paraResponse(Produto Produto) {
		return new ProdutoResponse(Produto.categoria(), Produto.nome(), Produto.descricao(), Produto.valor());
	}
}
