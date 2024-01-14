package com.fiap.lanchonete.infrastructure.controller;

import com.fiap.lanchonete.domain.entity.Pedido;

public class PedidoRequestMapper {
	Pedido paraObjetoDominio(PedidoRequest pedidoEntity) {
		return new Pedido(pedidoEntity.idPedido(), pedidoEntity.nomeLanche(), pedidoEntity.nomeAcompanhamento(), pedidoEntity.nomeBebida(), pedidoEntity.nomeSobremesa(),
				pedidoEntity.statusPedido());

	}
	
	PedidoResponse paraResponse(Pedido pedido) {
		return new PedidoResponse(pedido.idPedido(), pedido.nomeLanche(), pedido.nomeAcompanhamento(), pedido.nomeBebida(), pedido.nomeSobremesa(),
				pedido.statusPedido());
	}
}
