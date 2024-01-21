package com.fiap.lanchonete.application.usercases;

import java.util.List;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoUseCasesImp implements PedidoUseCases {

	private final PedidoGateway pedidoGateway;
	private final ProdutoGateway produtoGateway;

	public PedidoUseCasesImp(PedidoGateway pedidoGateway, ProdutoGateway produtoGateway) {
		this.pedidoGateway = pedidoGateway;
		this.produtoGateway = produtoGateway;
	}

	@Override
	public List<Pedido> buscaPedidos() {
		return pedidoGateway.buscaPedidos();
	}

	
	@Override
	public List<Pedido> buscaPedidosPorStatus(StatusPedido status) {
		return pedidoGateway.buscaPedidosStatus(status);
	}
	
	@Override
	public Pedido buscaPedidoId(Integer id) {
		return pedidoGateway.buscaPedidoId(id);
	}
	@Override
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

		Pedido pedidoParaCriar = new Pedido(pedido.idPedido(), pedido.nomeLanche(), pedido.nomeAcompanhamento(),
				pedido.nomeBebida(), pedido.nomeSobremesa(), StatusPedido.Recebido,
				StatusPagamento.EsperandoConfirmação);
		return pedidoGateway.criaPedido(pedidoParaCriar);
	}
	@Override
	public void atualizaPedido(Pedido pedido) throws PedidoNaoEncontradoException {

		Pedido pedidoParaAtualizar = pedidoGateway.buscaPedidoId(pedido.idPedido());

		if (pedidoParaAtualizar == null)
			throw new PedidoNaoEncontradoException();

		Pedido pedidoAtaulizado = new Pedido(pedidoParaAtualizar.idPedido(), pedido.nomeLanche(),
				pedido.nomeAcompanhamento(), pedido.nomeBebida(), pedido.nomeSobremesa(), pedido.statusPedido(),
				pedido.statusPagamento());

		pedidoGateway.atualizaPedido(pedidoAtaulizado);
	}
	
	@Override
	public void atualizaPedidoStatus(Integer id, StatusPedido status) throws PedidoNaoEncontradoException {
		Pedido pedidoParaAtualizar = pedidoGateway.buscaPedidoId(id);

		if (pedidoParaAtualizar == null)
			throw new PedidoNaoEncontradoException();

		Pedido pedidoAtaulizado = new Pedido(pedidoParaAtualizar.idPedido(), pedidoParaAtualizar.nomeLanche(),
				pedidoParaAtualizar.nomeAcompanhamento(), pedidoParaAtualizar.nomeBebida(),
				pedidoParaAtualizar.nomeSobremesa(), status, pedidoParaAtualizar.statusPagamento());
		pedidoGateway.atualizaPedido(pedidoAtaulizado);
	}
	
	@Override
	public String atualizaPedidoPagamneto(String topic, Integer id) {
		Pedido pedidoParaAtualizar = pedidoGateway.buscaPedidoId(id);
		Pedido pedidoAtaulizado;
		if (topic.equals("chargebacks")) {
			pedidoAtaulizado = new Pedido(pedidoParaAtualizar.idPedido(), pedidoParaAtualizar.nomeLanche(),
					pedidoParaAtualizar.nomeAcompanhamento(), pedidoParaAtualizar.nomeBebida(),
					pedidoParaAtualizar.nomeSobremesa(), StatusPedido.Finalizado, StatusPagamento.Cancelado);
			pedidoGateway.atualizaPedido(pedidoAtaulizado);
			return "Pedido cancelado";
		} else {
			pedidoAtaulizado = new Pedido(pedidoParaAtualizar.idPedido(), pedidoParaAtualizar.nomeLanche(),
					pedidoParaAtualizar.nomeAcompanhamento(), pedidoParaAtualizar.nomeBebida(),
					pedidoParaAtualizar.nomeSobremesa(), StatusPedido.EmPreparacao, StatusPagamento.Pago);
			pedidoGateway.atualizaPedido(pedidoAtaulizado);
			return "Pedido pago com sucesso";

		}

	}

}
