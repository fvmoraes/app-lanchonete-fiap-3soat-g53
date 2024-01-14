package com.fiap.lanchonete.infrastructure.gateway;

import java.util.List;
import java.util.Optional;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.dominio.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.infrastructure.persistence.PedidoEntity;
import com.fiap.lanchonete.infrastructure.persistence.PedidoRepository;
import com.fiap.lanchonete.infrastructure.persistence.ProdutoRepository;

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
	public Pedido CriaPedido(Pedido pedido) throws PedidoComProdutoNaoCadastradoException {

		if (pedido.nomeLanche() != null) {
			produtoRepository.findByNome(pedido.nomeLanche());
		}
		if (pedido.nomeBebida() != null) {
			produtoRepository.findByNome(pedido.nomeBebida());
		}
		if (pedido.nomeAcompanhamento() != null) {
			produtoRepository.findByNome(pedido.nomeAcompanhamento());
		}
		if (pedido.nomeSobremesa() != null) {
			produtoRepository.findByNome(pedido.nomeSobremesa());
		}
		repository.save(mapper.paraPedidoEntity(pedido));
		return pedido;
	}

	@Override
	public void atualizaPedido(Pedido pedido) throws PedidoNaoEncontradoException {
		Optional<PedidoEntity> pedidoParaAtualizar = repository.findById(pedido.idPedido());

		if (pedidoParaAtualizar.isEmpty())
			throw new PedidoNaoEncontradoException();
		
		
		PedidoEntity pedidoAtaulizado = pedidoParaAtualizar.get();
		pedidoAtaulizado.setNomeAcompanhamento(pedido.nomeAcompanhamento());
		pedidoAtaulizado.setNomeBebida(pedido.nomeBebida());
		pedidoAtaulizado.setNomeLanche(pedido.nomeLanche());
		pedidoAtaulizado.setStatusPedido(pedido.statusPedido());
		repository.save(pedidoAtaulizado);

	}

	@Override
	public List<Pedido> buscaPedidos() {
		List<PedidoEntity> pedidos = repository.findAll();
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
	public void atualizaPedidoStatus(Integer id, StatusPedido status) throws PedidoNaoEncontradoException {
		Optional<PedidoEntity> pedidoAtualizar = repository.findById(id);
		if (pedidoAtualizar.isPresent()) {
			PedidoEntity pedidoAtualizado = pedidoAtualizar.get();
			pedidoAtualizado.setStatusPedido(status);
			repository.save(pedidoAtualizado);
		}
	}

	@Override
	public List<Pedido> buscaPedidosStatus(StatusPedido status) {
		List<PedidoEntity> listaPedidos = repository.findAllByStatusPedidoOrderById(status);
		return listaPedidos.stream().map(mapper::paraObjetoDominio).toList();

	}
}