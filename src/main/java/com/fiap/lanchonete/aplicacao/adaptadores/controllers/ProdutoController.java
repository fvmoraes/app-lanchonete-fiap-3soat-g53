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

import com.fiap.lanchonete.dominio.Categoria;
import com.fiap.lanchonete.dominio.dtos.ProdutoDto;
import com.fiap.lanchonete.dominio.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.dominio.portas.interfaces.ProdutoServicePort;

@RestController
@RequestMapping("api/v1/produto")
public class ProdutoController {

	private final ProdutoServicePort service;

	public ProdutoController(ProdutoServicePort service) {
		this.service = service;
	}

	@GetMapping
	public List<ProdutoDto> buscarProdutos() {
		return service.buscarProdutos();
	}
	
	@GetMapping("{categoria}")
	public List<ProdutoDto> buscarProdutosCategoria(@PathVariable Categoria categoria) {
		return service.buscarProdutosCategoria(categoria);
	}
	
	@PostMapping
	public ResponseEntity<String> criarProduto(@RequestBody ProdutoDto dto) {
		try {
			service.cadastraProduto(dto);
			return new ResponseEntity<>("Produto cadastrado com sucesso", HttpStatus.CREATED);

		} catch (ProdutoJaCadastradoException e) {
			return new ResponseEntity<>("Produto já cadastrado", HttpStatus.CONFLICT);
		}
	}
	@PutMapping
	public ResponseEntity<String> atualizaProduto(@RequestBody ProdutoDto dto) {
		try {
			service.atualizaProduto(dto);
			return new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.OK);

		} catch (ProdutoNaoEncontradoException e) {
			return new ResponseEntity<>("Produto não cadastrado", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletaProduto(@RequestBody ProdutoDto dto) {
		service.deletaProduto(dto.getNome());
		return new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.OK);

	}
	@DeleteMapping("{nome}")
	public ResponseEntity<String> deletaProdutoNome(@PathVariable String nome) {
		service.deletaProduto(nome);
		return new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.OK);

	}
}
