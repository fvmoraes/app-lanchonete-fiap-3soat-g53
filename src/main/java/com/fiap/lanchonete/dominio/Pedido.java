package com.fiap.lanchonete.dominio;

public class Pedido {

	private Integer idPedido;
	private String nomeLanche;
	private String nomeAcompanhamento;
	private String nomeBebida;
	private String nomeSobremesa;
	private StatusPedido statusPedido;

	public Pedido(Integer idPedido, String nomeLanche, String nomeAcompanhamento, String nomeBebida,
			String nomeSobremesa, StatusPedido statusPedido) {
		
		this.idPedido = idPedido;
		this.nomeLanche = nomeLanche;
		this.nomeAcompanhamento = nomeAcompanhamento;
		this.nomeBebida = nomeBebida;
		this.nomeSobremesa = nomeSobremesa;
		this.statusPedido = statusPedido;
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

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public void setNomeLanche(String nomeLanche) {
		this.nomeLanche = nomeLanche;
	}

	public void setNomeAcompanhamento(String nomeAcompanhamento) {
		this.nomeAcompanhamento = nomeAcompanhamento;
	}

	public void setNomeBebida(String nomeBebida) {
		this.nomeBebida = nomeBebida;
	}

	public void setNomeSobremesa(String nomeSobremesa) {
		this.nomeSobremesa = nomeSobremesa;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

}
