package com.fiap.lanchonete.application.gateways;

import java.util.List;

import com.fiap.lanchonete.application.usercases.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;



public interface PedidoGateway {
	Pedido criaPedido(Pedido pedido) throws PedidoComProdutoNaoCadastradoException;

	void atualizaPedido(Pedido pedido) throws PedidoNaoEncontradoException;

	List<Pedido> buscaPedidos();

	Pedido buscaPedidoId(Integer id);

	List<Pedido> buscaPedidosStatus(StatusPedido status); 

}
