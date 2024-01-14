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

import com.fiap.lanchonete.application.usercases.ProdutoInteractor;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.ProdutoResponse;
import com.fiap.lanchonete.dominio.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ProdutoNaoEncontradoException;


@RestController
@RequestMapping("api/v1/produto")
public class ProdutoController {


	private final ProdutoInteractor interactor;
	private final ProdutoRequestMapper mapper;
	
	public ProdutoController(ProdutoInteractor interactor, ProdutoRequestMapper mapper) {
		this.interactor = interactor;
		this.mapper = mapper;
	}
	@GetMapping
	public List<ProdutoResponse> buscarProdutos() {
		return interactor.buscarProdutos().stream().map(mapper::paraResponse).toList();
	}
	
	@GetMapping("{categoria}")
	public List<ProdutoResponse> buscarProdutosCategoria(@PathVariable Categoria categoria) {
		return interactor.buscarProdutosCategoria(categoria).stream().map(mapper::paraResponse).toList();
	}
	
	@PostMapping
	public ResponseEntity<String> criarProduto(@RequestBody ProdutoRequest produtoRequest) {
		try {
			interactor.cadastraProduto(mapper.paraObjetoDominio(produtoRequest));
			return new ResponseEntity<>("Produto cadastrado com sucesso", HttpStatus.CREATED);

		} catch (ProdutoJaCadastradoException e) {
			return new ResponseEntity<>("Produto já cadastrado", HttpStatus.CONFLICT);
		}
	}
	@PutMapping
	public ResponseEntity<String> atualizaProduto(@RequestBody ProdutoRequest produtoRequest) {
		try {
			interactor.atualizaProduto(mapper.paraObjetoDominio(produtoRequest));
			return new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.OK);

		} catch (ProdutoNaoEncontradoException e) {
			return new ResponseEntity<>("Produto não cadastrado", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletaProduto(@RequestBody ProdutoRequest produtoRequest) {
		interactor.deletaProduto(produtoRequest.nome());
		return new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.OK);

	}
	@DeleteMapping("{nome}")
	public ResponseEntity<String> deletaProdutoNome(@PathVariable String nome) {
		interactor.deletaProduto(nome);
		return new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.OK);

	}
}
