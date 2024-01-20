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
import com.fiap.lanchonete.application.usercases.exceptions.ClientJaCadastradoException;
import com.fiap.lanchonete.application.usercases.exceptions.ClientNaoEncontradoException;
import com.fiap.lanchonete.infrastructure.controller.mapper.ClenteRequestMapper;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.ClenteRequest;
import com.fiap.lanchonete.infrastructure.controller.requestsdto.ClienteResponse;

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
	public List<ClienteResponse> buscarClientes() {
		return interactor.buscarClientes().stream().map(mapper::paraResponse).toList();	
	}

	@GetMapping("{cpf}")
	public ResponseEntity<ClienteResponse> buscaClienteCpf(@PathVariable String cpf) {
			var cliente = interactor.buscaClienteCpf(cpf);
			if (cliente != null) {
				return new ResponseEntity<> (mapper.paraResponse(cliente),HttpStatus.FOUND);
			}
				return new ResponseEntity<> (null,HttpStatus.NOT_FOUND);
			
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
			interactor.atualizaCliente(mapper.paraCliente(dto));
			return new ResponseEntity<>("Cliente atualizado com sucesso", HttpStatus.OK);

		} catch (ClientNaoEncontradoException e) {
			return new ResponseEntity<>("Cliente não cadastrado", HttpStatus.BAD_REQUEST);
		}
	}
}