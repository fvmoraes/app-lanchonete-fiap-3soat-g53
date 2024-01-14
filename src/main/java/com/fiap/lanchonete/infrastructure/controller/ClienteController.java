package com.fiap.lanchonete.infrastructure.controller;

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

import com.fiap.lanchonete.application.usercases.ClienteInteractor;
import com.fiap.lanchonete.domain.entity.Cliente;
import com.fiap.lanchonete.dominio.exceptions.ClientJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ClientNaoEncontradoException;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

	private final ClienteInteractor interactor;
	private final ClenteRequestMapper mapper;
	public ClienteController(ClienteInteractor interactor, ClenteRequestMapper mapper) {
		this.interactor = interactor;
		this.mapper = mapper;
	}

	@GetMapping
	public List<Cliente> buscarClientes() {
		return interactor.buscarClientes();
	}

	@GetMapping("{cpf}")
	public Cliente buscaClienteCpf(@PathVariable String cpf) {
			return interactor.buscaClienteCpf(cpf);
	}

	@PostMapping
	public ResponseEntity<String> criarCliente(@RequestBody ClenteRequest clientRequest) {
		try {
			interactor.criaCliente(mapper.paraCliente(clientRequest));
			return new ResponseEntity<>("Cliente cadastrado com sucesso", HttpStatus.CREATED);
		} catch (ClientJaCadastradoException e) {
			return new ResponseEntity<>("Cliente já cadastrado", HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletaCliente(@RequestBody ClenteRequest dto) {
		interactor.deletaCliente(mapper.paraCliente(dto));
			return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
	
	}

	@DeleteMapping("{cpf}")
	public ResponseEntity<String> deletaClienteCpf(@PathVariable String cpf) {
		interactor.deletaCliente(cpf);
			return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
			}

	@PutMapping
	public ResponseEntity<String> atualizaClientes(@RequestBody ClenteRequest dto) {
		try {
			interactor.AtualizaCliente(mapper.paraCliente(dto));
			return new ResponseEntity<>("Cliente atualizado com sucesso", HttpStatus.OK);

		} catch (ClientNaoEncontradoException e) {
			return new ResponseEntity<>("Cliente não cadastrado", HttpStatus.BAD_REQUEST);
		}
	}
}