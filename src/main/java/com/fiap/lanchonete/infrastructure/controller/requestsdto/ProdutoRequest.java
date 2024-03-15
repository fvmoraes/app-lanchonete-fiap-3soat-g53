package com.fiap.lanchonete.infrastructure.controller.requestsdto;

import java.math.BigDecimal;

import com.fiap.lanchonete.domain.entity.Categoria;

public class ProdutoRequest {

	Categoria categoria;
	String nome;
	String descricao;
	BigDecimal valor;
	
	public ProdutoRequest(Categoria categoria, String nome, String descricao, BigDecimal valor) {
		super();
		this.categoria = categoria;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
