package com.fiap.lanchonete.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lanchonete.application.usercases.PedidoInteractor;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.controller.mapper.PedidoRequestMapper;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.PedidoPagamentoResponse;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.PedidoRequest;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.PedidoResponse;

//hexa
@RestController
@RequestMapping("api/v1/pedido")
public class PedidoController {


	private final PedidoInteractor interactor;
	private final PedidoRequestMapper mapper;
	
	public PedidoController(PedidoInteractor interactor, PedidoRequestMapper mapper) {
		this.interactor = interactor;
		this.mapper = mapper;
	}

	
	@GetMapping
	public List<PedidoResponse> buscaPedidos() {
		return interactor.buscaPedidos().stream().map(mapper::paraResponse).toList();
	};
	
	@GetMapping("{id}")
	public PedidoResponse buscaPedidosPorId(@PathVariable Integer id) {
		return  mapper.paraResponse(interactor.buscaPedidoId(id));		
	};
	@GetMapping("pagamento/{id}")
	public PedidoPagamentoResponse buscaPedidosPagamento(@PathVariable Integer id) {
		return  mapper.paraResponseDTO(interactor.buscaPedidoId(id));		
	};
	
	@GetMapping("/status")
	public List<PedidoResponse> buscaPedidosPorStatus(@RequestBody StatusPedido status) {
		return interactor.buscaPedidosPorStatus(status).stream().map(mapper::paraResponse).toList();		
	};
	
	
	// FAKE CHECKOUT 
	@PostMapping
	public ResponseEntity<String> realizarPedido(@RequestBody PedidoRequest pedido){
		try {
			PedidoResponse response = mapper.paraResponse(interactor.realizaPedido(mapper.paraObjetoDominio(pedido)));
			return new ResponseEntity<>(response.toString(), HttpStatus.ACCEPTED);
		} catch (PedidoComProdutoNaoCadastradoException e) {
			return new ResponseEntity<>("Pedido Com Produto Nao Cadastrado Exception",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<String> atualizaPedido(@RequestBody PedidoRequest pedido){
		try {
			interactor.atualizaPedido(mapper.paraObjetoDominio(pedido));
			return new ResponseEntity<>("Pedido atualizado com sucesso", HttpStatus.ACCEPTED);
		} catch (PedidoNaoEncontradoException e) {
			return new ResponseEntity<>("Pedido não encontrado",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("status/{id}/{statusRequest}")
	public ResponseEntity<String> atualizaPedidoStatus(@PathVariable Integer id, @PathVariable String statusRequest){
		try {
			
			interactor.atualizaPedidoStatus(id, StatusPedido.valueOf(statusRequest));
			return new ResponseEntity<>(String.format("Pedido atualizado para" + statusRequest.toString()+ "com sucesso", statusRequest), HttpStatus.ACCEPTED);
		} catch (PedidoNaoEncontradoException e) {
			return new ResponseEntity<>("Pedido não encontrado",HttpStatus.BAD_REQUEST);
		}
	}
	//WEBHOOK
	@PostMapping("mercadopago")
	ResponseEntity<String> webHookMercadoPagoSimulator(Integer id) throws PedidoNaoEncontradoException{
		interactor.atualizaPedidoPagamneto(id);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("accepted");
		
	}
	
}
