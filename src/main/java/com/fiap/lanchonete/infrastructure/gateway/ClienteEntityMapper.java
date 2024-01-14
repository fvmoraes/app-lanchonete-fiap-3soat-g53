package com.fiap.lanchonete.infrastructure.gateway;

import com.fiap.lanchonete.domain.entity.Cliente;
import com.fiap.lanchonete.infrastructure.persistence.ClienteEntity;

public class ClienteEntityMapper {
	ClienteEntity paraEntity (Cliente clienteDomainObject) {
		return new ClienteEntity(clienteDomainObject.cpf(), clienteDomainObject.nome(), clienteDomainObject.email() );
	}
	Cliente paraObject(ClienteEntity clienteEntity) {
		return new Cliente(clienteEntity.getCpf(), clienteEntity.getNome(), clienteEntity.getEmail());
	}
}
