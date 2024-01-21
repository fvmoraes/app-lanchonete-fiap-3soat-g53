package com.fiap.lanchonete.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.lanchonete.application.gateways.ClienteGateway;
import com.fiap.lanchonete.application.usercases.ClienteUseCases;
import com.fiap.lanchonete.application.usercases.ClienteUseCasesImp;
import com.fiap.lanchonete.infrastructure.controller.mapper.ClienteRequestMapper;
import com.fiap.lanchonete.infrastructure.gateway.ClienteRespositoryGateway;
import com.fiap.lanchonete.infrastructure.gateway.mapper.ClienteEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.ClienteRepository;

@Configuration
public class ClientConfig {

	@Bean
	ClienteUseCases clientInteractorBean(ClienteGateway clienteGateway) {
		return new ClienteUseCasesImp(clienteGateway);
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
	ClienteRequestMapper clienteRequestMapper() {
		return new ClienteRequestMapper();
	}
}
