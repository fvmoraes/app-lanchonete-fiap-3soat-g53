package com.fiap.lanchonete.infrastructure.controller.mapper;

import com.fiap.lanchonete.domain.entity.Cliente;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.ClenteRequest;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.ClienteResponse;

public class ClenteRequestMapper {

	public Cliente paraCliente(ClenteRequest request) {
		return new Cliente(request.cpf(),request.nome(), request.email());
	}
	
	public ClienteResponse paraResponse(Cliente cliente) {
		return new ClienteResponse(cliente.cpf(),cliente.nome(), cliente.email());
	}
}
