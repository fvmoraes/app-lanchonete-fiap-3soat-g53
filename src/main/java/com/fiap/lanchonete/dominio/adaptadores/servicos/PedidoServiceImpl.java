package com.fiap.lanchonete.dominio.adaptadores.servicos;

import java.util.List;
import java.util.stream.Collectors;

import com.fiap.lanchonete.dominio.Pedido;
import com.fiap.lanchonete.dominio.StatusPedido;
import com.fiap.lanchonete.dominio.dtos.PedidoDto;
import com.fiap.lanchonete.dominio.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.dominio.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.dominio.portas.interfaces.PedidoServicePort;
import com.fiap.lanchonete.dominio.portas.interfaces.ProdutoServicePort;
import com.fiap.lanchonete.dominio.portas.repositorios.PedidoRepositoryPort;

public class PedidoServiceImpl implements PedidoServicePort {
	PedidoRepositoryPort repository;
	ProdutoServicePort produtoService;

	public PedidoServiceImpl(PedidoRepositoryPort repository, ProdutoServicePort produtoService) {
		this.repository = repository;
		this.produtoService = produtoService;
	}

	@Override
	public List<PedidoDto> buscaPedidos() {
		List<Pedido> listaPedidos = repository.buscaPedidos();
		List<PedidoDto> produtosDto = listaPedidos.stream()
				.map(pedido -> new PedidoDto(pedido.getIdPedido(), pedido.getNomeLanche(),
						pedido.getNomeAcompanhamento(), pedido.getNomeBebida(), pedido.getNomeSobremesa(),
						pedido.getStatusPedido())
				).collect(Collectors.toList());
		return produtosDto;
	}
	
	@Override
	public List<PedidoDto> buscaPedidosStatus(StatusPedido status) {
		List<Pedido> listaPedidos = repository.buscaPedidosStatus(status);
		List<PedidoDto> produtosDto = listaPedidos.stream()
				.map(pedido -> new PedidoDto(pedido.getIdPedido(), pedido.getNomeLanche(),
						pedido.getNomeAcompanhamento(), pedido.getNomeBebida(), pedido.getNomeSobremesa(),
						pedido.getStatusPedido())
				).collect(Collectors.toList());
		return produtosDto;

	}
 
	@Override
	public PedidoDto buscaPedidoId(Integer id) {
		Pedido pedido = repository.buscaPedidoId(id);
		
		return new PedidoDto(pedido.getIdPedido(), pedido.getNomeLanche(), pedido.getNomeAcompanhamento(), pedido.getNomeBebida(), pedido.getNomeSobremesa(), pedido.getStatusPedido());
	}

	@Override
	public PedidoDto realizaPedido(PedidoDto pedido) throws PedidoComProdutoNaoCadastradoException {
		
		try {
			if (pedido.getNomeLanche() != null) {
				produtoService.buscaProdutoNome(pedido.getNomeLanche());
			}
			if (pedido.getNomeBebida() != null) {
				produtoService.buscaProdutoNome(pedido.getNomeBebida());
			}
			if (pedido.getNomeAcompanhamento() != null) {
				produtoService.buscaProdutoNome(pedido.getNomeAcompanhamento());
			}
			if (pedido.getNomeSobremesa() != null) {
				produtoService.buscaProdutoNome(pedido.getNomeSobremesa());
			}
		} catch (ProdutoNaoEncontradoException e) {
			throw new PedidoComProdutoNaoCadastradoException();
		}
		repository.salvarPedido(pedido.toPedido());
		return pedido;
	}

	@Override
	public void atualizaPedido(PedidoDto pedido) throws PedidoNaoEncontradoException {
		Pedido pedidoAtualizado = repository.buscaPedidoId(pedido.getIdPedido());
		if (pedidoAtualizado == null)
			throw new PedidoNaoEncontradoException();
		
		pedidoAtualizado.setNomeAcompanhamento(pedido.getNomeAcompanhamento());
		pedidoAtualizado.setNomeBebida(pedido.getNomeBebida());
		pedidoAtualizado.setNomeLanche(pedido.getNomeLanche());	
		pedidoAtualizado.setStatusPedido(pedido.getStatus());		
		repository.salvarPedido(pedidoAtualizado);
	}

	@Override
	public void atualizaPedidoStatus(Integer id, StatusPedido status) throws PedidoNaoEncontradoException {
		Pedido pedidoAtualizado = repository.buscaPedidoId(id);
		pedidoAtualizado.setStatusPedido(status);		
		repository.atualizaPedido(pedidoAtualizado);		
	}
	
}
