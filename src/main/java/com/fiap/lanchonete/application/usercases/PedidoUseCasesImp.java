package com.fiap.lanchonete.application.usercases;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
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
	public Pedido buscaPedidoId(Integer id) throws PedidoNaoEncontradoException {
		var pedido = pedidoGateway.buscaPedidoId(id);
		if (pedido == null)
			throw new PedidoNaoEncontradoException();

		return pedido;
	}
	
	@Override
	public Pedido realizaPedido(Pedido pedido) throws PedidoComProdutoNaoCadastradoException {
		
		 if (pedido.getListaProdutos().stream()
		            .anyMatch(produto -> produtoGateway.buscarPeloNome(produto.getNome()) == null)) {
		        throw new PedidoComProdutoNaoCadastradoException();
		    }
		List<Produto> pedidoComTodosDados =   pedido.getListaProdutos().stream().map(produto -> produtoGateway.buscarPeloNome(produto.getNome())).collect(Collectors.toList());
		Pedido pedidoParaCriar = new Pedido(pedido.getId(),pedidoComTodosDados, StatusPedido.Recebido,
				StatusPagamento.EsperandoConfirmação, calculaValorTotal(pedidoComTodosDados.stream().map(produto -> produto.getValor()).collect(Collectors.toList())));
		
		return pedidoGateway.criaPedido(pedidoParaCriar);
	}
	@Override
	public void atualizaPedido(Pedido pedido) throws PedidoNaoEncontradoException {

		Pedido pedidoParaAtualizar = pedidoGateway.buscaPedidoId(pedido.getId());

		if (pedidoParaAtualizar == null)
			throw new PedidoNaoEncontradoException();

		List<Produto> pedidoComTodosDados = pedido.getListaProdutos().stream().map(produto -> produtoGateway.buscarPeloNome(produto.getNome())).collect(Collectors.toList());

		
		Pedido pedidoAtaulizado = new Pedido(pedidoParaAtualizar.getId(),  pedidoComTodosDados, pedido.getStatusPedido(),
				pedido.getStatusPagamento(),calculaValorTotal(pedidoComTodosDados.stream().map(produto -> produto.getValor()).collect(Collectors.toList())));

		pedidoGateway.atualizaPedido(pedidoAtaulizado);
	}
	
	@Override
	public void atualizaPedidoStatus(Integer id, StatusPedido status) throws PedidoNaoEncontradoException {
		Pedido pedidoParaAtualizar = pedidoGateway.buscaPedidoId(id);

		if (pedidoParaAtualizar == null)
			throw new PedidoNaoEncontradoException();

		Pedido pedidoAtaulizado = new Pedido(pedidoParaAtualizar.getId(), pedidoParaAtualizar.getListaProdutos(), status, pedidoParaAtualizar.getStatusPagamento(), pedidoParaAtualizar.getValorTotal());
		pedidoGateway.atualizaPedido(pedidoAtaulizado);
	}
	
	@Override
	public String atualizaPedidoPagamneto(String topic, Integer id) {
		Pedido pedidoParaAtualizar = pedidoGateway.buscaPedidoId(id);
		Pedido pedidoAtaulizado;
		if (topic.equals("chargebacks")) {
			pedidoAtaulizado = new Pedido(pedidoParaAtualizar.getId(), 
					pedidoParaAtualizar.getListaProdutos(), StatusPedido.Finalizado, StatusPagamento.Cancelado,pedidoParaAtualizar.getValorTotal());
			pedidoGateway.atualizaPedido(pedidoAtaulizado);
			return "Pedido cancelado";
		} else {
			pedidoAtaulizado = new Pedido(pedidoParaAtualizar.getId(), 
					pedidoParaAtualizar.getListaProdutos(), StatusPedido.EmPreparacao, StatusPagamento.Pago, pedidoParaAtualizar.getValorTotal());
			pedidoGateway.atualizaPedido(pedidoAtaulizado);
			return "Pedido pago com sucesso";

		}

	}

	@Override
	public BigDecimal calculaValorTotal(List<BigDecimal> valores) {
		return valores.stream()
        .reduce(BigDecimal.ZERO, BigDecimal::add);
		 
	}

}
