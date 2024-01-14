package com.fiap.lanchonete.application.gateways;

import java.util.List;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.dominio.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.PedidoNaoEncontradoException;



public interface PedidoGateway {
	Pedido CriaPedido(Pedido pedido) throws PedidoComProdutoNaoCadastradoException;

	void atualizaPedido(Pedido pedido) throws PedidoNaoEncontradoException;

	List<Pedido> buscaPedidos();

	Pedido buscaPedidoId(Integer id);

	void atualizaPedidoStatus(Integer id, StatusPedido status) throws PedidoNaoEncontradoException;

	List<Pedido> buscaPedidosStatus(StatusPedido status); 

}
