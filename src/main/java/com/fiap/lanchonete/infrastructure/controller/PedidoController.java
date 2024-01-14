package com.fiap.lanchonete.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lanchonete.application.usercases.PedidoInteractor;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.dominio.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.PedidoNaoEncontradoException;

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
	@GetMapping("/status")
	public List<PedidoResponse> buscaPedidosPorStatus(@RequestBody StatusPedido status) {
		return interactor.buscaPedidosPorStatus(status).stream().map(mapper::paraResponse).toList();		
	};
	
	
	// FAKE CHECKOUT deverá ser implementado o pagamento junto ao realizar o pedido
	@PostMapping
	public ResponseEntity<PedidoResponse> realizarPedido(@RequestBody PedidoRequest pedido){
		try {
			PedidoResponse response = mapper.paraResponse(interactor.realizaPedido(mapper.paraObjetoDominio(pedido)));
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} catch (PedidoComProdutoNaoCadastradoException e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
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
	
	@PutMapping("status/{id}")
	public ResponseEntity<String> atualizaPedidoStatus(@PathVariable Integer id, @RequestBody StatusPedido status){
		try {
			interactor.atualizaPedidoStatus(id, status);
			return new ResponseEntity<>(String.format("Pedido atualizado para" + status.toString()+ "com sucesso", status), HttpStatus.ACCEPTED);
		} catch (PedidoNaoEncontradoException e) {
			return new ResponseEntity<>("Pedido não encontrado",HttpStatus.BAD_REQUEST);
		}
	}
	
}
