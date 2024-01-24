package com.fiap.lanchonete.infrastructure.persistence.entity;

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
    
    String email;
    
	public ClienteEntity() {
	}
	
	public ClienteEntity(String cpf, String nome, String email) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

}
