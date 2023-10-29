package com.fiap.lanchonete.aplicacao.adaptadores.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lanchonete.dominio.dtos.ClienteDto;
import com.fiap.lanchonete.dominio.exceptions.ClientJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ClientNaoEncontradoException;
import com.fiap.lanchonete.dominio.portas.interfaces.ClienteServicePort;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

	private final ClienteServicePort service;

	public ClienteController(ClienteServicePort service) {
		this.service = service;
	}

	@GetMapping
	public List<ClienteDto> buscarClientes() {
		return service.buscarClientes();
	}

	@GetMapping("{cpf}")
	public ClienteDto buscaClienteCpf(@PathVariable String cpf) {
			return service.buscaClienteCpf(cpf);
	}

	@PostMapping
	public ResponseEntity<String> criarCliente(@RequestBody ClienteDto dto) {
		try {
			service.CriaCliente(dto);
			return new ResponseEntity<>("Cliente cadastrado com sucesso", HttpStatus.CREATED);
		} catch (ClientJaCadastradoException e) {
			return new ResponseEntity<>("Cliente já cadastrado", HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletaCliente(@RequestBody ClienteDto dto) {
			service.DeletaCliente(dto);
			return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
	
	}

	@DeleteMapping("{cpf}")
	public ResponseEntity<String> deletaClienteCpf(@PathVariable String cpf) {
			service.DeletaCliente(cpf);
			return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
			}

	@PutMapping
	public ResponseEntity<String> atualizaClientes(@RequestBody ClienteDto dto) {
		try {
			service.AtualizaCliente(dto);
			return new ResponseEntity<>("Cliente atualizado com sucesso", HttpStatus.OK);

		} catch (ClientNaoEncontradoException e) {
			return new ResponseEntity<>("Cliente não cadastrado", HttpStatus.BAD_REQUEST);
		}
	}
}
