package com.fiap.lanchonete.infrastructure.controller.requestsdto;

import com.fiap.lanchonete.domain.entity.StatusPagamento;

	public record PedidoPagamentoResponse(Integer idPedido, StatusPagamento statusPagamento) {
}
