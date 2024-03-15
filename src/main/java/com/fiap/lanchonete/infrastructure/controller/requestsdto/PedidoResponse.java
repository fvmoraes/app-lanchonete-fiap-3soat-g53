package com.fiap.lanchonete.infrastructure.controller.requestsdto;

import java.util.List;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoResponse {

	Integer idPedido;

	List<Produto>listaProdutos;
	StatusPedido statusPedido;
	StatusPagamento statusPagamento;

	public PedidoResponse(Integer idPedido, List<Produto>listaProdutos, StatusPedido statusPedido,
			StatusPagamento statusPagamento) {
		super();
		this.idPedido = idPedido;
		this.listaProdutos = listaProdutos;
		this.statusPedido = statusPedido;
		this.statusPagamento = statusPagamento;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public List<Produto>getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto>listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

}
