package com.fiap.lanchonete.aplicacao.adaptadores.controllers;

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

import com.fiap.lanchonete.dominio.StatusPedido;
import com.fiap.lanchonete.dominio.dtos.PedidoDto;
import com.fiap.lanchonete.dominio.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.dominio.portas.interfaces.PedidoServicePort;


@RestController
@RequestMapping("api/v1/pedido")
public class PedidoController {

	private final PedidoServicePort service;

	public PedidoController(PedidoServicePort service) {
		this.service = service;
	}
	
	@GetMapping
	public List<PedidoDto> buscaPedidos() {
		return service.buscaPedidos();
		
	};
	
	@GetMapping("{id}")
	public PedidoDto buscaPedidosPorId(@PathVariable Integer id) {
		return service.buscaPedidoId(id);		
	};
	@GetMapping("/status")
	public List<PedidoDto> buscaPedidosPorStatus(@RequestBody StatusPedido status) {
		return service.buscaPedidosStatus(status);		
	};
	
	
	// FAKE CHECKOUT deverá ser implementado o pagamento junto ao realizar o pedido
	@PostMapping
	public ResponseEntity<String> realizarPedido(@RequestBody PedidoDto pedido){
		try {
			service.realizaPedido(pedido);
			return new ResponseEntity<>("Pedido realizado com sucesso", HttpStatus.ACCEPTED);
		} catch (PedidoComProdutoNaoCadastradoException e) {
			return new ResponseEntity<>("Pedido foi realizado com produto nao cadastrado",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<String> atualizaPedido(@RequestBody PedidoDto pedido){
		try {
			service.atualizaPedido(pedido);
			return new ResponseEntity<>("Pedido atualizado com sucesso", HttpStatus.ACCEPTED);
		} catch (PedidoNaoEncontradoException e) {
			return new ResponseEntity<>("Pedido não encontrado",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("status/{id}")
	public ResponseEntity<String> atualizaPedidoStatus(@PathVariable Integer id, @RequestBody StatusPedido status){
		try {
			service.atualizaPedidoStatus(id, status);
			return new ResponseEntity<>(String.format("Pedido atualizado para" + status.toString()+ "com sucesso", status), HttpStatus.ACCEPTED);
		} catch (PedidoNaoEncontradoException e) {
			return new ResponseEntity<>("Pedido não encontrado",HttpStatus.BAD_REQUEST);
		}
	}
	
}
