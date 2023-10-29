package com.fiap.lanchonete.dominio.dtos;

import com.fiap.lanchonete.dominio.Cliente;

public class ClienteDto {
	String cpf;
	String nome;
	
	public ClienteDto(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}
	
	public Cliente toClient() {
		return new Cliente(this.cpf.replaceAll("[^0-9]", ""),this.nome);
	}
	
}
