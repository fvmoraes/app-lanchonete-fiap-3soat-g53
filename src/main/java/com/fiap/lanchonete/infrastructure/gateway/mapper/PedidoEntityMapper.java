package com.fiap.lanchonete.infrastructure.gateway.mapper;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

public class PedidoEntityMapper {
	ProdutoEntityMapper mapperProduct = new ProdutoEntityMapper();
	public PedidoEntity paraPedidoEntity(Pedido PedidoObjectDomain) {
	return new PedidoEntity(PedidoObjectDomain.getId() ,PedidoObjectDomain.getListaProdutos(),PedidoObjectDomain.getStatusPedido(), PedidoObjectDomain.getStatusPagamento());

	}
	
	public Pedido paraObjetoDominio(PedidoEntity pedidoEntity) {
		return new Pedido(pedidoEntity.getId(), pedidoEntity.getListaProdutosPedido(),
				pedidoEntity.getStatusPedido(), pedidoEntity.getStatusPagamento());
	}
}