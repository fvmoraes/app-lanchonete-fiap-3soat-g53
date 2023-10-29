package com.fiap.lanchonete.infraestrutura.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.lanchonete.dominio.adaptadores.servicos.ClienteServiceImpl;
import com.fiap.lanchonete.dominio.adaptadores.servicos.PedidoServiceImpl;
import com.fiap.lanchonete.dominio.adaptadores.servicos.ProdutoServiceImpl;
import com.fiap.lanchonete.dominio.portas.interfaces.ClienteServicePort;
import com.fiap.lanchonete.dominio.portas.interfaces.PedidoServicePort;
import com.fiap.lanchonete.dominio.portas.interfaces.ProdutoServicePort;
import com.fiap.lanchonete.dominio.portas.repositorios.ClienteRepositoryPort;
import com.fiap.lanchonete.dominio.portas.repositorios.PedidoRepositoryPort;
import com.fiap.lanchonete.dominio.portas.repositorios.ProdutoRepositoryPort;

@Configuration
public class BeanConfiguracao {

	@Bean
	public ProdutoServicePort produtoService(ProdutoRepositoryPort port) {
		return new ProdutoServiceImpl(port);
	}
	
	@Bean
	public ClienteServicePort clienteService(ClienteRepositoryPort port) {
		return new ClienteServiceImpl(port);
	}
	
	@Bean
	public PedidoServicePort pedidoService(PedidoRepositoryPort port, ProdutoServicePort portservice) {
		return new PedidoServiceImpl(port, portservice);
	}
}
