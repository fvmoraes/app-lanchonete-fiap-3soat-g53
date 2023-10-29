package com.fiap.lanchonete.infraestrutura.entidades;

import java.math.BigDecimal;

import com.fiap.lanchonete.dominio.Categoria;
import com.fiap.lanchonete.dominio.Produto;

import jakarta.persistence.Entity;
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
    private Categoria categoria;
   
    @NotNull(message = "valor não pode ser nulo")
	private BigDecimal valor;

	public ProdutoEntity() {
		super();
	}

	public ProdutoEntity(Categoria categoria, String nome, String descricao, BigDecimal valor) {
		this.categoria = categoria;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
	}



	public Produto toProduto() {
		return new Produto(this.categoria, this.getNome(), this.descricao, this.valor);
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
