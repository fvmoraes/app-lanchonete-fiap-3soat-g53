package com.fiap.lanchonete.dominio.portas.interfaces;

import java.util.List;

import com.fiap.lanchonete.dominio.StatusPedido;
import com.fiap.lanchonete.dominio.dtos.PedidoDto;
import com.fiap.lanchonete.dominio.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.PedidoNaoEncontradoException;

public interface PedidoServicePort {
	PedidoDto realizaPedido(PedidoDto pedido) throws PedidoComProdutoNaoCadastradoException;

	void atualizaPedido(PedidoDto pedido) throws PedidoNaoEncontradoException;

	List<PedidoDto> buscaPedidos();

	PedidoDto buscaPedidoId(Integer id);

	void atualizaPedidoStatus(Integer id, StatusPedido status) throws PedidoNaoEncontradoException;

	List<PedidoDto> buscaPedidosStatus(StatusPedido status); 

}
