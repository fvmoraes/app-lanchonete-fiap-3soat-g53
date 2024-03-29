package com.fiap.lanchonete.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usercases.ProdutoUseCases;
import com.fiap.lanchonete.application.usercases.ProdutoUseCasesImp;
import com.fiap.lanchonete.infrastructure.controller.mapper.ProdutoRequestMapper;
import com.fiap.lanchonete.infrastructure.gateway.ProdutoRespositoryGateway;
import com.fiap.lanchonete.infrastructure.gateway.mapper.ProdutoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.ProdutoRepository;

@Configuration
public class ProdutoConfig {
	
	@Bean
	ProdutoUseCases produtoInteractorBean(ProdutoGateway clienteGateway) {
		return new ProdutoUseCasesImp(clienteGateway);
	}

	@Bean
	ProdutoGateway produtoGateway(ProdutoEntityMapper mapper, ProdutoRepository repository) {
		return new ProdutoRespositoryGateway(repository, mapper);
	}

	@Bean
	ProdutoEntityMapper produtoMapper() {
		return new ProdutoEntityMapper();
	}

	@Bean
	ProdutoRequestMapper produtoRequestMapper() {
		return new ProdutoRequestMapper();
	}
}

