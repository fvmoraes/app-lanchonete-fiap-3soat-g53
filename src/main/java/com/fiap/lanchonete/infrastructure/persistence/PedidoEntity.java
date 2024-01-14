package com.fiap.lanchonete.infrastructure.persistence;



import com.fiap.lanchonete.domain.entity.StatusPedido;

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
			@NotNull StatusPedido statusPedido2) {
		this.id = id;
		this.nomeLanche = nomeLanche;
		this.nomeAcompanhamento = nomeAcompanhamento;
		this.nomeBebida = nomeBebida;
		this.nomeSobremesa = nomeSobremesa;
		this.statusPedido = statusPedido2;
	}
	
	public PedidoEntity( String nomeLanche, String nomeAcompanhamento, String nomeBebida, String nomeSobremesa,
			@NotNull StatusPedido statusPedido) {
		this.nomeLanche = nomeLanche;
		this.nomeAcompanhamento = nomeAcompanhamento;
		this.nomeBebida = nomeBebida;
		this.nomeSobremesa = nomeSobremesa;
		this.statusPedido = statusPedido;
	}
	
	public Integer getId() {
		return id;
	}
	public String getNomeLanche() {
		return nomeLanche;
	}
	public void setNomeLanche(String nomeLanche) {
		this.nomeLanche = nomeLanche;
	}
	public String getNomeAcompanhamento() {
		return nomeAcompanhamento;
	}
	public void setNomeAcompanhamento(String nomeAcompanhamento) {
		this.nomeAcompanhamento = nomeAcompanhamento;
	}
	public String getNomeBebida() {
		return nomeBebida;
	}
	public void setNomeBebida(String nomeBebida) {
		this.nomeBebida = nomeBebida;
	}
	public String getNomeSobremesa() {
		return nomeSobremesa;
	}
	public void setNomeSobremesa(String nomeSobremesa) {
		this.nomeSobremesa = nomeSobremesa;
	}
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	
	
	
}
