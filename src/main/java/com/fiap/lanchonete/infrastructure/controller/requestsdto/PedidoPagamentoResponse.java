package com.fiap.lanchonete.infrastructure.controller.requestsdto;

import java.math.BigDecimal;

import com.fiap.lanchonete.domain.entity.StatusPagamento;

public class PedidoPagamentoResponse {
	Integer idPedido;
	StatusPagamento statusPagamento;
	BigDecimal valorTotal;

	public PedidoPagamentoResponse(Integer idPedido, StatusPagamento statusPagamento,BigDecimal valorTotal) {
		super();
		this.idPedido = idPedido;
		this.statusPagamento = statusPagamento;
		this.valorTotal = valorTotal;

	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}
	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
	
}
