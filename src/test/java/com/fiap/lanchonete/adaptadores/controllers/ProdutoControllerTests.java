package com.fiap.lanchonete.adaptadores.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fiap.lanchonete.aplicacao.adaptadores.controllers.ProdutoController;
import com.fiap.lanchonete.dominio.Categoria;
import com.fiap.lanchonete.dominio.dtos.ProdutoDto;
import com.fiap.lanchonete.dominio.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.dominio.portas.interfaces.ProdutoServicePort;

@SpringBootTest
public class ProdutoControllerTests {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoServicePort produtoService;

    @Test
    public void testCriarProdutoComSucesso() throws ProdutoJaCadastradoException {
        ProdutoDto produtoDto = new ProdutoDto(Categoria.Bebida,"Coca-cola","Bebida gelada a base de cola para se apreciar",BigDecimal.valueOf(9.80));
        doNothing().when(produtoService).cadastraProduto(produtoDto);

        ResponseEntity<String> responseEntity = produtoController.criarProduto(produtoDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Produto cadastrado com sucesso", responseEntity.getBody());
    }

    @Test
    public void testCriarProdutoJaCadastrado() throws ProdutoJaCadastradoException {
        ProdutoDto produtoDto = new ProdutoDto(Categoria.Bebida,"Coca-cola","Bebida gelada a base de cola para se apreciar",BigDecimal.valueOf(9.80));
        doThrow(ProdutoJaCadastradoException.class).when(produtoService).cadastraProduto(produtoDto);

        ResponseEntity<String> responseEntity = produtoController.criarProduto(produtoDto);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Produto já cadastrado", responseEntity.getBody());
    }

    @Test
    public void testAtualizaProdutoComSucesso() throws ProdutoNaoEncontradoException {
        ProdutoDto produtoDto = new ProdutoDto(Categoria.Bebida,"Coca-cola","Bebida gelada a base de cola para se apreciar",BigDecimal.valueOf(9.80));
        doNothing().when(produtoService).atualizaProduto(produtoDto);

        ResponseEntity<String> responseEntity = produtoController.atualizaProduto(produtoDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Produto atualizado com sucesso", responseEntity.getBody());
    }

    @Test
    public void testAtualizaProdutoNaoEncontrado() throws ProdutoNaoEncontradoException {
        ProdutoDto produtoDto = new ProdutoDto(Categoria.Bebida,"Coca-cola","Bebida gelada a base de cola para se apreciar",BigDecimal.valueOf(9.80));
        doThrow(ProdutoNaoEncontradoException.class).when(produtoService).atualizaProduto(produtoDto);

        ResponseEntity<String> responseEntity = produtoController.atualizaProduto(produtoDto);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Produto não cadastrado", responseEntity.getBody());
    }

    @Test
    public void testBuscarProdutosComSucesso() {
    	List<ProdutoDto> produtos = new ArrayList<>();
		produtos.add(new ProdutoDto(Categoria.Acompanhamento,"Café", "Saboroso café", BigDecimal.valueOf(13.99)));
		produtos.add(new ProdutoDto(Categoria.Sobremesa,"Achocolatado", "Saboroso achocolatado", BigDecimal.valueOf(18.99)));
		produtos.add(new ProdutoDto(Categoria.Bebida,"Coca-cola", "bebida feita a base de cola", BigDecimal.valueOf(8.99)));

        when(produtoService.buscarProdutos()).thenReturn(produtos);

        List<ProdutoDto> result = produtoController.buscarProdutos();
        assertEquals(produtos, result);
    }
}
