package com.fiap.lanchonete.application.usercases;

import java.util.List;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.dominio.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.PedidoNaoEncontradoException;

public class PedidoInteractor {

	
	private final PedidoGateway pedidoGateway;

	
	public PedidoInteractor(PedidoGateway pedidoGateway) {
		this.pedidoGateway = pedidoGateway;
	}


	public List<Pedido> buscaPedidos() {
	return pedidoGateway.buscaPedidos();
	}

	public List<Pedido> buscaPedidosPorStatus(StatusPedido status) {
	return pedidoGateway.buscaPedidosStatus(status);
	}
	
	
	public Pedido buscaPedidoId(Integer id) {
		Pedido pedido = pedidoGateway.buscaPedidoId(id);
		
		return pedido;
	}

	public Pedido realizaPedido(Pedido pedido) throws PedidoComProdutoNaoCadastradoException {
		
		return pedidoGateway.CriaPedido(pedido);
	}
	
	public void atualizaPedido(Pedido pedido) throws PedidoNaoEncontradoException {
		pedidoGateway.atualizaPedido(pedido);
	}

	public void atualizaPedidoStatus(Integer id, StatusPedido status) throws PedidoNaoEncontradoException {
		pedidoGateway.atualizaPedidoStatus(id, status);
	}
	
}
