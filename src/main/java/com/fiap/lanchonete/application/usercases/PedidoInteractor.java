package com.fiap.lanchonete.application.usercases;

import java.util.List;
import java.util.Optional;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

public class PedidoInteractor {

	
	private final PedidoGateway pedidoGateway;
	private final ProdutoGateway produtoGateway;

	
	public PedidoInteractor(PedidoGateway pedidoGateway, ProdutoGateway produtoGateway) {
		this.pedidoGateway = pedidoGateway;
		this.produtoGateway = produtoGateway;
	}


	public List<Pedido> buscaPedidos() {
	return pedidoGateway.buscaPedidos();
	}
	
	public List<Pedido> buscaPedidosPorStatus(StatusPedido status) {
	return pedidoGateway.buscaPedidosStatus(status);
	}
	
	
	public Pedido buscaPedidoId(Integer id) {
		return pedidoGateway.buscaPedidoId(id);
		}

	public Pedido realizaPedido(Pedido pedido) throws PedidoComProdutoNaoCadastradoException {
		if (pedido.nomeLanche() != null && produtoGateway.buscarPeloNome(pedido.nomeLanche()) == null) {
				throw new PedidoComProdutoNaoCadastradoException();
		}
		if (pedido.nomeBebida() != null && produtoGateway.buscarPeloNome(pedido.nomeBebida()) == null) {			
				throw new PedidoComProdutoNaoCadastradoException();
		}
		if (pedido.nomeAcompanhamento() != null && produtoGateway.buscarPeloNome(pedido.nomeAcompanhamento()) == null) {
			throw new PedidoComProdutoNaoCadastradoException();

		}
		if (pedido.nomeSobremesa() != null && produtoGateway.buscarPeloNome(pedido.nomeSobremesa()) == null) {
				throw new PedidoComProdutoNaoCadastradoException();
		}
		
		Pedido pedidoParaCriar = new Pedido(pedido.idPedido(), pedido.nomeLanche(), pedido.nomeAcompanhamento(), pedido.nomeBebida(), pedido.nomeSobremesa(), StatusPedido.Recebido, StatusPagamento.EsperandoConfirmação);
		return pedidoGateway.criaPedido(pedidoParaCriar);
	}
	
	public void atualizaPedido(Pedido pedido) throws PedidoNaoEncontradoException {
		
	    Pedido pedidoParaAtualizar = pedidoGateway.buscaPedidoId(pedido.idPedido());

		if (pedidoParaAtualizar == null)
			throw new PedidoNaoEncontradoException();
		
		Pedido pedidoAtaulizado = new Pedido(pedidoParaAtualizar.idPedido(), pedido.nomeLanche(), pedido.nomeAcompanhamento(), pedido.nomeBebida(), pedido.nomeSobremesa(), pedido.statusPedido(), pedido.statusPagamento());
		
		pedidoGateway.atualizaPedido(pedidoAtaulizado);
	}

	public void atualizaPedidoStatus(Integer id, StatusPedido status) throws PedidoNaoEncontradoException {
	    Pedido pedidoParaAtualizar = pedidoGateway.buscaPedidoId(id);

			if (pedidoParaAtualizar == null)
				throw new PedidoNaoEncontradoException();
			
			Pedido pedidoAtaulizado = new Pedido(pedidoParaAtualizar.idPedido(), pedidoParaAtualizar.nomeLanche(), pedidoParaAtualizar.nomeAcompanhamento(), pedidoParaAtualizar.nomeBebida(), pedidoParaAtualizar.nomeSobremesa(),status, pedidoParaAtualizar.statusPagamento());
			pedidoGateway.atualizaPedido(pedidoAtaulizado);
	}


	public void atualizaPedidoPagamneto(Integer id) throws PedidoNaoEncontradoException {
	    Pedido pedidoParaAtualizar = pedidoGateway.buscaPedidoId(id);

		if (pedidoParaAtualizar == null)
			throw new PedidoNaoEncontradoException();
	
		Pedido pedidoAtaulizado = new Pedido(pedidoParaAtualizar.idPedido(), pedidoParaAtualizar.nomeLanche(), pedidoParaAtualizar.nomeAcompanhamento(), pedidoParaAtualizar.nomeBebida(), pedidoParaAtualizar.nomeSobremesa(),StatusPedido.EmPreparacao, StatusPagamento.Pago);
		pedidoGateway.atualizaPedido(pedidoAtaulizado);

	}
	
}
