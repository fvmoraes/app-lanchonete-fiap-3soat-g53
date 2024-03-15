package com.fiap.lanchonete.domain.entity;

import java.util.List;

public class Pedido {
	
	Integer id;
	List<String> listaProdutos;
	StatusPedido statusPedido;
	StatusPagamento statusPagamento;
	public Pedido(Integer id, List<String> listaProdutos, StatusPedido statusPedido,
			StatusPagamento statusPagamento) {
	
		this.id = id;
		this.listaProdutos = listaProdutos;
		this.statusPedido = statusPedido;
		this.statusPagamento = statusPagamento;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<String> listaProdutos) {
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
