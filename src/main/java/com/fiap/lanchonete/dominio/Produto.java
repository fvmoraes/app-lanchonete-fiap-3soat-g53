package com.fiap.lanchonete.dominio;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.fiap.lanchonete.dominio.dtos.ProdutoDto;

public class Produto {

	Categoria categoria;

	String nome;

	String descricao;

	private BigDecimal valor;

	// Construtor
	public Produto(Categoria categoria, String nome, String descricao, BigDecimal valor) {
		this.nome = nome;
		this.categoria = categoria;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Produto(ProdutoDto dto) {
		this.nome = dto.getNome();
		this.descricao = dto.getDescricao();
		this.valor = dto.getValor();
		this.categoria = dto.getCategoria();
	}

	// Métodos de acesso (getters e setters)
	public String getNome() {
		return nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return "Produto [nome=" + nome + ", descricao=" + descricao + ", valor=" + nf.format(valor) + "]";
	}
}
