package com.fiap.lanchonete.infrastructure.controller;

import com.fiap.lanchonete.domain.entity.Cliente;

public class ClenteRequestMapper {

	Cliente paraCliente(ClenteRequest request) {
		return new Cliente(request.cpf(),request.nome(), request.email());
	}
}
