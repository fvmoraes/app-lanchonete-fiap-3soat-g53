package com.fiap.lanchonete.infrastructure.controller.mapper;

import com.fiap.lanchonete.domain.entity.Cliente;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.ClienteRequest;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.ClienteResponse;

public class ClienteRequestMapper {

	public Cliente paraCliente(ClienteRequest request) {
		return new Cliente(request.getCpf(),request.getNome(), request.getEmail());
	}
	
	public ClienteResponse paraResponse(Cliente cliente) {
		return new ClienteResponse(cliente.getCpf(),cliente.getNome(), cliente.getEmail());
	}
}
