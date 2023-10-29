package com.fiap.lanchonete.dominio.dtos;

import java.math.BigDecimal;

import com.fiap.lanchonete.dominio.Categoria;


public class ProdutoDto {
	
	private Categoria categoria;
	
	private String nome;
	
	private String descricao;
	
    private BigDecimal valor;
    

	public ProdutoDto(Categoria categoria, String nome, String descricao, BigDecimal valor) {
		super();
		this.categoria = categoria;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
	}

	
	public Categoria getCategoria() {
		return categoria;
	}


	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}


}
