package com.fiap.lanchonete.infrastructure.gateway;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.infrastructure.persistence.PedidoEntity;

public class PedidoEntityMapper {
	
	PedidoEntity paraPedidoEntity(Pedido PedidoObjectDomain) {
	return new PedidoEntity(PedidoObjectDomain.nomeLanche(), PedidoObjectDomain.nomeAcompanhamento(), PedidoObjectDomain.nomeBebida(), PedidoObjectDomain.nomeSobremesa(),
			PedidoObjectDomain.statusPedido());

	}
	
	Pedido paraObjetoDominio(PedidoEntity pedidoEntity) {
		return new Pedido(pedidoEntity.getId(), pedidoEntity.getNomeLanche(), pedidoEntity.getNomeAcompanhamento(), pedidoEntity.getNomeBebida(), pedidoEntity.getNomeSobremesa(),
				pedidoEntity.getStatusPedido());

	}
}