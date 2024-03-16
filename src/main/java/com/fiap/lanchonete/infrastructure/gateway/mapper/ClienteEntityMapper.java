package com.fiap.lanchonete.infrastructure.gateway.mapper;

import com.fiap.lanchonete.domain.entity.Cliente;
import com.fiap.lanchonete.infrastructure.persistence.entity.ClienteEntity;

public class ClienteEntityMapper {
	
	public ClienteEntity paraEntity (Cliente clienteDomainObject) {
		return new ClienteEntity(clienteDomainObject.getCpf(), clienteDomainObject.getNome(), clienteDomainObject.getEmail() );
	}
	public Cliente paraObject(ClienteEntity clienteEntity) {
		return new Cliente(clienteEntity.getCpf(), clienteEntity.getNome(), clienteEntity.getEmail());
	}
}
