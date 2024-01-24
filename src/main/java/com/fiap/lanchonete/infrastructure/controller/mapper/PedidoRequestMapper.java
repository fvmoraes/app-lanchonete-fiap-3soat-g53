package com.fiap.lanchonete.infrastructure.controller.mapper;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.PedidoPagamentoResponse;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.PedidoRequest;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.PedidoResponse;

public class PedidoRequestMapper {
	
	public Pedido paraObjetoDominio(PedidoRequest pedidoEntity) {
		return new Pedido(pedidoEntity.idPedido(), pedidoEntity.nomeLanche(), pedidoEntity.nomeAcompanhamento(), pedidoEntity.nomeBebida(), pedidoEntity.nomeSobremesa(),
				pedidoEntity.statusPedido(), pedidoEntity.statusPagamento());

	}
	
	public PedidoResponse paraResponse(Pedido pedido) {
		return new PedidoResponse(pedido.idPedido(), pedido.nomeLanche(), pedido.nomeAcompanhamento(), pedido.nomeBebida(), pedido.nomeSobremesa(),
				pedido.statusPedido(), pedido.statusPagamento());
	}
	public PedidoPagamentoResponse paraResponseDTO(Pedido pedido) {
		return new PedidoPagamentoResponse(pedido.idPedido(), pedido.statusPagamento());
	}
}
