package com.fiap.lanchonete.application.usercases;

import java.math.BigDecimal;
import java.util.List;

import com.fiap.lanchonete.application.usercases.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public interface PedidoUseCases {

	public List<Pedido> buscaPedidos();
	public List<Pedido> buscaPedidosPorStatus(StatusPedido status);
	public Pedido buscaPedidoId(Integer id) throws PedidoNaoEncontradoException;
	public Pedido realizaPedido(Pedido pedido) throws PedidoComProdutoNaoCadastradoException;
	public void atualizaPedido(Pedido pedido) throws PedidoNaoEncontradoException;
	public void atualizaPedidoStatus(Integer id, StatusPedido status) throws PedidoNaoEncontradoException;
	public String atualizaPedidoPagamneto(String topic, Integer id);
	public BigDecimal calculaValorTotal(List<BigDecimal> valores);
}
