package com.fiap.lanchonete.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usercases.PedidoInteractor;
import com.fiap.lanchonete.infrastructure.controller.mapper.PedidoRequestMapper;
import com.fiap.lanchonete.infrastructure.gateway.PedidoRepositoryGateway;
import com.fiap.lanchonete.infrastructure.gateway.mapper.PedidoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.PedidoRepository;
import com.fiap.lanchonete.infrastructure.persistence.ProdutoRepository;

@Configuration
public class PedidoConfig {

	@Bean
	PedidoInteractor pedidoInteractorBean(PedidoGateway PedidoGateway, ProdutoGateway produtoGateway) {
		return new PedidoInteractor(PedidoGateway, produtoGateway);
	}

	@Bean
	PedidoGateway pedidoGateway(PedidoEntityMapper mapper, PedidoRepository repository, ProdutoRepository repositoryProduct) {
		return new PedidoRepositoryGateway(repository, mapper, repositoryProduct);
	}

	@Bean
	PedidoEntityMapper pedidoMapper() {
		return new PedidoEntityMapper();
	}

	@Bean
	PedidoRequestMapper pedidoRequestMapper() {
		return new PedidoRequestMapper();
	}
}
