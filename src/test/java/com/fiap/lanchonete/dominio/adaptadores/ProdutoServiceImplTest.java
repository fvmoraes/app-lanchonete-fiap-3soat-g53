package com.fiap.lanchonete.dominio.adaptadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.lanchonete.dominio.Categoria;
import com.fiap.lanchonete.dominio.Produto;
import com.fiap.lanchonete.dominio.adaptadores.servicos.ProdutoServiceImpl;
import com.fiap.lanchonete.dominio.dtos.ProdutoDto;
import com.fiap.lanchonete.dominio.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.dominio.portas.repositorios.ProdutoRepositoryPort;



@SpringBootTest
public class ProdutoServiceImplTest {
	@InjectMocks
	private ProdutoServiceImpl produtoService;

	@Mock
	private ProdutoRepositoryPort produtoRepository;

	@Test
	public void testBuscarProdutos() {
		List<Produto> produtos = new ArrayList<>();
		produtos.add(new Produto(Categoria.Acompanhamento,"Café", "Saboroso café", BigDecimal.valueOf(13.99)));
		produtos.add(new Produto(Categoria.Sobremesa,"Achocolatado", "Saboroso achocolatado", BigDecimal.valueOf(18.99)));
		produtos.add(new Produto(Categoria.Bebida,"Coca-cola", "bebida feita a base de cola", BigDecimal.valueOf(8.99)));

		when(produtoRepository.buscarTodos()).thenReturn(produtos);

		List<ProdutoDto> result = produtoService.buscarProdutos();

		assertEquals(result.get(0).getCategoria(), produtos.get(0).getCategoria());
		assertEquals(result.get(0).getNome(), produtos.get(0).getNome());
		assertEquals(result.get(0).getValor(), produtos.get(0).getValor());
		assertEquals(result.get(0).getDescricao(), produtos.get(0).getDescricao());

		
		assertEquals(result.get(1).getCategoria(), produtos.get(1).getCategoria());
		assertEquals(result.get(1).getNome(), produtos.get(1).getNome());
		assertEquals(result.get(1).getValor(), produtos.get(1).getValor());
		assertEquals(result.get(1).getDescricao(), produtos.get(1).getDescricao());
		
		
		assertEquals(result.get(2).getCategoria(), produtos.get(2).getCategoria());
		assertEquals(result.get(2).getNome(), produtos.get(2).getNome());
		assertEquals(result.get(2).getValor(), produtos.get(2).getValor());
		assertEquals(result.get(2).getDescricao(), produtos.get(2).getDescricao());

	}

	@Test
	public void testCadastraProdutoComSucesso() throws ProdutoJaCadastradoException {
		ProdutoDto produto = new ProdutoDto(Categoria.Lanche, "Hamburguer de Frango", "Hamburguer delicioso", BigDecimal.valueOf(35.99));
		when(produtoRepository.buscarPeloNome("Hamburguer de Frango")).thenReturn(null);

		produtoService.cadastraProduto(produto);
		// Verifique se o método salvar no repositório foi chamado
	}

	@Test()
	public void testCadastraProdutoJaCadastrado() throws ProdutoJaCadastradoException {
		ProdutoDto produto = new ProdutoDto(Categoria.Lanche, "Hamburguer de Frango", "Hamburguer delicioso", BigDecimal.valueOf(35.99));
		when(produtoRepository.buscarPeloNome("Hamburguer de Frango")).thenReturn(new Produto(Categoria.Lanche, "Hamburguer de Frango", "Hamburguer delicioso", BigDecimal.valueOf(35.99)));
	
	    assertThrows(ProdutoJaCadastradoException.class, () -> 	produtoService.cadastraProduto(produto));
	}

	@Test
	public void testAtualizaProdutoComSucesso() throws ProdutoNaoEncontradoException {
		ProdutoDto produto = new ProdutoDto(Categoria.Lanche, "Hamburguer_de_Frango", "Hamburguer delicioso e suculento", BigDecimal.valueOf(40.99));
		when(produtoRepository.buscarPeloNome("Hamburguer_de_Frango")).thenReturn(new Produto(Categoria.Lanche, "Hamburguer_de_Frango", "Hamburguer delicioso", BigDecimal.valueOf(35.99)));
		
		produtoService.atualizaProduto(produto);
		var atualizado = produtoService.buscaProdutoNome("Hamburguer_de_Frango");
		
		assertEquals(atualizado.getDescricao(), produto.getDescricao());
		assertEquals(atualizado.getValor(), produto.getValor());
		
		
	}

	@Test()
	public void testAtualizaProdutoNaoEncontrado() throws ProdutoNaoEncontradoException {
		ProdutoDto produtodto = new ProdutoDto(Categoria.Lanche, "Hamburguer de Frango", "Hamburguer delicioso", BigDecimal.valueOf(35.99));
		when(produtoRepository.buscarPeloNome("Hamburguer de Frango")).thenReturn(null);

	    assertThrows(ProdutoNaoEncontradoException.class, () -> 	produtoService.atualizaProduto(produtodto));
	}

	@Test
	public void testBuscaProdutoNomeComSucesso() throws ProdutoNaoEncontradoException {
		Produto produto = new Produto(Categoria.Lanche, "Hamburguer de Frango", "Hamburguer delicioso", BigDecimal.valueOf(35.99));
		when(produtoRepository.buscarPeloNome("Hamburguer de Frango")).thenReturn(produto);

		ProdutoDto result = produtoService.buscaProdutoNome("Hamburguer de Frango");
		
		assertEquals(result.getCategoria(), produto.getCategoria());
		assertEquals(result.getNome(), produto.getNome());
		assertEquals(result.getValor(), produto.getValor());
		assertEquals(result.getDescricao(), produto.getDescricao());
	}
}
