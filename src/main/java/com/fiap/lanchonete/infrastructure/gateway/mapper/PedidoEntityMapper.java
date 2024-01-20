package com.fiap.lanchonete.infrastructure.gateway.mapper;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

public class PedidoEntityMapper {
	
	public PedidoEntity paraPedidoEntity(Pedido PedidoObjectDomain) {
	return new PedidoEntity(PedidoObjectDomain.idPedido(), PedidoObjectDomain.nomeLanche(), PedidoObjectDomain.nomeAcompanhamento(), PedidoObjectDomain.nomeBebida(), PedidoObjectDomain.nomeSobremesa(),
			PedidoObjectDomain.statusPedido(), PedidoObjectDomain.statusPagamento());

	}
	
	public Pedido paraObjetoDominio(PedidoEntity pedidoEntity) {
		return new Pedido(pedidoEntity.getId(), pedidoEntity.getNomeLanche(), pedidoEntity.getNomeAcompanhamento(), pedidoEntity.getNomeBebida(), pedidoEntity.getNomeSobremesa(),
				pedidoEntity.getStatusPedido(), pedidoEntity.getStatusPagamento());

	}
}