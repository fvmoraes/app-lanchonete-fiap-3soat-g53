package com.fiap.lanchonete.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.lanchonete.application.gateways.ClienteGateway;
import com.fiap.lanchonete.application.usercases.ClienteInteractor;
import com.fiap.lanchonete.infrastructure.controller.ClenteRequestMapper;
import com.fiap.lanchonete.infrastructure.gateway.ClienteEntityMapper;
import com.fiap.lanchonete.infrastructure.gateway.ClienteRespositoryGateway;
import com.fiap.lanchonete.infrastructure.persistence.ClienteRepository;

@Configuration
public class ClientConfig {

	@Bean
	ClienteInteractor clientInteractorBean(ClienteGateway clienteGateway) {
		return new ClienteInteractor(clienteGateway);
	}

	@Bean
	ClienteGateway clienteGateway(ClienteEntityMapper mapper, ClienteRepository repository) {
		return new ClienteRespositoryGateway(repository, mapper);
	}

	@Bean
	ClienteEntityMapper clienteMapper() {
		return new ClienteEntityMapper();
	}

	@Bean
	ClenteRequestMapper clienteRequestMapper() {
		return new ClenteRequestMapper();
	}
}
