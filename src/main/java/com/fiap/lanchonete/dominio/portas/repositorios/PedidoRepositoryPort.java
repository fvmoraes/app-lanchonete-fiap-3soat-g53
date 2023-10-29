package com.fiap.lanchonete.dominio.portas.repositorios;

import java.util.List;

import com.fiap.lanchonete.dominio.Pedido;
import com.fiap.lanchonete.dominio.StatusPedido;

public interface PedidoRepositoryPort {

	void salvarPedido(Pedido pedido);

	List<Pedido> buscaPedidos();

	Pedido buscaPedidoId(Integer id);

	List<Pedido> buscaPedidosStatus(StatusPedido statusPedido);

}
