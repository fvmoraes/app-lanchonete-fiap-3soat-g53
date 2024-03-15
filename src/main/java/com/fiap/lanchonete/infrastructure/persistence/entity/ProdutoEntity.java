package com.fiap.lanchonete.infrastructure.persistence.entity;

import java.math.BigDecimal;

import com.fiap.lanchonete.domain.entity.Categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {
	
	@Id
    private String nome;

	private String descricao;
	
	@NotNull(message = "categoria não pode ser nulo")
	@Enumerated(EnumType.STRING)
    private Categoria categoria;
   
    @NotNull(message = "valor não pode ser nulo")
	private BigDecimal valor;

	public ProdutoEntity() {
	}

	public ProdutoEntity(Categoria categoria, String nome, String descricao, BigDecimal valor) {
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
