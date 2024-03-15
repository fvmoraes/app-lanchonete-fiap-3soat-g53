package com.fiap.lanchonete.infrastructure.persistence.entity;



import java.util.List;

import org.springframework.context.annotation.Lazy;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.persistence.converters.ProdutoListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Lazy
@Table(name = "pedidos")
public class PedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer id;

    @Convert(converter = ProdutoListConverter.class)
    List<Produto> listaProdutosPedido;
	 
	@NotNull
	@Enumerated(EnumType.STRING)
	StatusPedido statusPedido;
	
	@NotNull
	@Enumerated(EnumType.STRING)
    StatusPagamento statusPagamento;
	
	public PedidoEntity() {
		
	}

	public PedidoEntity(List<Produto> listaProdutosPedido, @NotNull StatusPedido statusPedido,
			@NotNull StatusPagamento statusPagamento) {
		this.listaProdutosPedido = listaProdutosPedido;
		this.statusPedido = statusPedido;
		this.statusPagamento = statusPagamento;
	}
	public PedidoEntity(Integer id,List<Produto> listaProdutosPedido, @NotNull StatusPedido statusPedido,
			@NotNull StatusPagamento statusPagamento) {
		this.id = id;
		this.listaProdutosPedido = listaProdutosPedido;
		this.statusPedido = statusPedido;
		this.statusPagamento = statusPagamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Produto> getListaProdutosPedido() {
		return listaProdutosPedido;
	}

	public void setListaProdutosPedido(List<Produto> listaProdutosPedido) {
		this.listaProdutosPedido = listaProdutosPedido;
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
