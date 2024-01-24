package com.fiap.lanchonete.infrastructure.controller.mapper;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.ProdutoResponse;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.ProdutoRequest;

public class ProdutoRequestMapper {
	
	public Produto paraObjetoDominio(ProdutoRequest produtoRequest) {
		return new Produto(produtoRequest.categoria(), produtoRequest.nome(), produtoRequest.descricao(), produtoRequest.valor());

	}
	
	public ProdutoResponse paraResponse(Produto Produto) {
		return new ProdutoResponse(Produto.categoria(), Produto.nome(), Produto.descricao(), Produto.valor());
	}
}
