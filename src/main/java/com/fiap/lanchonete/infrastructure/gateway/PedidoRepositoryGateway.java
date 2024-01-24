package com.fiap.lanchonete.infrastructure.gateway;

import java.util.List;
import java.util.Optional;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.gateway.mapper.PedidoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.PedidoRepository;
import com.fiap.lanchonete.infrastructure.persistence.ProdutoRepository;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

public class PedidoRepositoryGateway implements PedidoGateway {

	private final PedidoRepository repository;
	private final PedidoEntityMapper mapper;
	private final ProdutoRepository produtoRepository;

	public PedidoRepositoryGateway(PedidoRepository repository, PedidoEntityMapper mapper,
			ProdutoRepository produtoRepository) {
		this.repository = repository;
		this.mapper = mapper;
		this.produtoRepository = produtoRepository;
	}

	@Override
	public Pedido criaPedido(Pedido pedido) throws PedidoComProdutoNaoCadastradoException {
		return mapper.paraObjetoDominio(repository.save(mapper.paraPedidoEntity(pedido)));
	}

	@Override
	public void atualizaPedido(Pedido pedido) {
		repository.save(mapper.paraPedidoEntity(pedido));

	}

	@Override
	public List<Pedido> buscaPedidos() {
		List<PedidoEntity> pedidos = repository.findAllByStatusPedidoOrderById(StatusPedido.Pronto);
		pedidos.addAll(repository.findAllByStatusPedidoOrderById(StatusPedido.EmPreparacao).stream().toList());
		pedidos.addAll(repository.findAllByStatusPedidoOrderById(StatusPedido.Recebido).stream().toList());

		return pedidos.stream().map(mapper::paraObjetoDominio).toList();
	}

	@Override
	public Pedido buscaPedidoId(Integer id) {
		Optional<PedidoEntity> pedidos = repository.findById(id);
		if (pedidos.isPresent())
			return mapper.paraObjetoDominio(pedidos.get());
		return null;
	}

	@Override
	public List<Pedido> buscaPedidosStatus(StatusPedido status) {
		List<PedidoEntity> listaPedidos = repository.findAllByStatusPedidoOrderById(status);
		return listaPedidos.stream().map(mapper::paraObjetoDominio).toList();

	}
}