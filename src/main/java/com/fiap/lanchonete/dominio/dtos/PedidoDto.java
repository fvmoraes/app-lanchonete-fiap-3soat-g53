package com.fiap.lanchonete.dominio.dtos;

import com.fiap.lanchonete.dominio.Pedido;
import com.fiap.lanchonete.dominio.StatusPedido;

public class PedidoDto {
	
	Integer idPedido;
	String nomeLanche;
	String nomeAcompanhamento;
	String nomeBebida;
	String nomeSobremesa;
	StatusPedido status;
	
	public PedidoDto(Integer idPedido, String nomeLanche, String acompanhamento, String bebida, String sobremesa,StatusPedido status) {
		this.idPedido = idPedido;
		this.nomeLanche = nomeLanche;
		this.nomeAcompanhamento = acompanhamento;
		this.nomeBebida = bebida;
		this.nomeSobremesa = sobremesa;
		this.status = status;
	}
	
	public Integer getIdPedido() {
		return idPedido;
	}
	public String getNomeLanche() {
		return nomeLanche;
	}
	public String getNomeAcompanhamento() {
		return nomeAcompanhamento;
	}
	public String getNomeBebida() {
		return nomeBebida;
	}
	public String getNomeSobremesa() {
		return nomeSobremesa;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public Pedido toPedido() {
	return new Pedido(this.idPedido, this.nomeLanche,this.nomeAcompanhamento, this.nomeBebida, this.nomeSobremesa, this.status != null? this.status : StatusPedido.Recebido);
	}
}
