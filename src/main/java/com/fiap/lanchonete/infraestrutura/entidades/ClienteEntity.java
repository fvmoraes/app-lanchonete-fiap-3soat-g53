package com.fiap.lanchonete.infraestrutura.entidades;

import com.fiap.lanchonete.dominio.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clientes")
public class ClienteEntity {

	@Id
	String cpf;
	
    @NotNull(message = "Nome não pode ser nulo")
	String nome;

	public ClienteEntity() {
	}
	
	public ClienteEntity(String cpf, String nome) {
		super();
		this.cpf = cpf;
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public Cliente toCliente() {
		return new Cliente(this.cpf, this.nome);
	}

}
