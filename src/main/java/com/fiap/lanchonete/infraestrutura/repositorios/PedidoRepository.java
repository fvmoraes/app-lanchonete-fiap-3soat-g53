package com.fiap.lanchonete.infraestrutura.repositorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fiap.lanchonete.dominio.Pedido;
import com.fiap.lanchonete.dominio.StatusPedido;
import com.fiap.lanchonete.dominio.portas.repositorios.PedidoRepositoryPort;
import com.fiap.lanchonete.infraestrutura.entidades.PedidoEntity;

@Component
public class PedidoRepository implements PedidoRepositoryPort{

	SpringPedidoRepository repository;

	
	public PedidoRepository(SpringPedidoRepository repository) {
		this.repository = repository;
	}


	@Override
	public List<Pedido> buscaPedidos() {
		List<PedidoEntity> pedidos =  repository.findAll();
		return pedidos.stream()
			    .map(pedido -> pedido.toPedido())
			    .collect(Collectors.toList());
			}
	
	@Override
	public void salvarPedido(Pedido pedido) {
		repository.save(new PedidoEntity(pedido.getNomeLanche(), pedido.getNomeAcompanhamento(), pedido.getNomeBebida(), pedido.getNomeSobremesa(), pedido.getStatusPedido()));		
	}

	@Override
	public void atualizaPedido(Pedido pedido) {
		repository.save(new PedidoEntity(pedido.getIdPedido(), pedido.getNomeLanche(), pedido.getNomeAcompanhamento(), pedido.getNomeBebida(), pedido.getNomeSobremesa(), pedido.getStatusPedido()));		
	}
	@Override
	public Pedido buscaPedidoId(Integer id) {
		var pedido = repository.findById(id);
		return pedido.isPresent() ? pedido.get().toPedido() : null ;
	}


	@Override
	public List<Pedido> buscaPedidosStatus(StatusPedido statusPedido) {
		List<PedidoEntity> pedidos =  repository.findAllByStatusPedidoOrderById(null);
		return pedidos.stream()
			    .map(pedido -> pedido.toPedido())
			    .collect(Collectors.toList());

	}



}
