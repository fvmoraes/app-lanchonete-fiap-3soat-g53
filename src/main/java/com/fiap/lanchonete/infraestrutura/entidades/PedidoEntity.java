package com.fiap.lanchonete.infraestrutura.entidades;

import com.fiap.lanchonete.dominio.Pedido;
import com.fiap.lanchonete.dominio.StatusPedido;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer id;
	
	String nomeLanche;
	String nomeAcompanhamento;
	String nomeBebida;
	String nomeSobremesa;

	@NotNull
	StatusPedido statusPedido;
	
	
	public PedidoEntity() {
		
	}
	public PedidoEntity(Integer id, String nomeLanche, String nomeAcompanhamento, String nomeBebida, String nomeSobremesa,
			@NotNull StatusPedido statusPedido) {
		this.id = id;
		this.nomeLanche = nomeLanche;
		this.nomeAcompanhamento = nomeAcompanhamento;
		this.nomeBebida = nomeBebida;
		this.nomeSobremesa = nomeSobremesa;
		this.statusPedido = statusPedido;
	}
	
	public PedidoEntity( String nomeLanche, String nomeAcompanhamento, String nomeBebida, String nomeSobremesa,
			@NotNull StatusPedido statusPedido) {
		this.nomeLanche = nomeLanche;
		this.nomeAcompanhamento = nomeAcompanhamento;
		this.nomeBebida = nomeBebida;
		this.nomeSobremesa = nomeSobremesa;
		this.statusPedido = statusPedido;
	}

	public Pedido toPedido() {	
		return new Pedido(this.id,this.nomeLanche, this.nomeAcompanhamento, this.nomeBebida, this.nomeSobremesa, this.statusPedido);
	}
}
