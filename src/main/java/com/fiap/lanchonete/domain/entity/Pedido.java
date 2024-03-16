package com.fiap.lanchonete.domain.entity;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {
	
	Integer id;
	List<Produto> listaProdutos;
	StatusPedido statusPedido;
	StatusPagamento statusPagamento;
	BigDecimal valorTotal;
	
	public Pedido(Integer id, List<Produto> listaProdutos, StatusPedido statusPedido,
			StatusPagamento statusPagamento,  BigDecimal valorTotal) {
	
		this.id = id;
		this.listaProdutos = listaProdutos;
		this.statusPedido = statusPedido;
		this.statusPagamento = statusPagamento;
		this.valorTotal = valorTotal;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
