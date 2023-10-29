package com.fiap.lanchonete.infraestrutura.repositorios;

import static org.junit.Assert.assertEquals;
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
import com.fiap.lanchonete.infraestrutura.entidades.ProdutoEntity;
@SpringBootTest
public class ProdutoRepositoryTest {
	
	
	@InjectMocks
    private ProdutoRespository produtoRepository;

    @Mock
    private SpringProdutoRepository springProdutoRepository;

  

    @Test
    public void testBuscarTodos() {
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        produtoEntities.add(new ProdutoEntity(Categoria.Acompanhamento,"Café", "Saboroso café", BigDecimal.valueOf(13.99)));
    	produtoEntities.add(new ProdutoEntity(Categoria.Sobremesa,"Achocolatado", "Saboroso achocolatado", BigDecimal.valueOf(18.99)));
    	produtoEntities.add(new ProdutoEntity(Categoria.Bebida,"Coca-cola", "bebida feita a base de cola", BigDecimal.valueOf(8.99)));

        when(springProdutoRepository.findAll()).thenReturn(produtoEntities);

        List<Produto> result = produtoRepository.buscarTodos();
   
        assertEquals(produtoEntities.get(0).getNome(), result.get(0).getNome());
        assertEquals(produtoEntities.get(1).getNome(), result.get(1).getNome());
        assertEquals(produtoEntities.get(2).getNome(), result.get(2).getNome());

    }

    @Test
    public void testBuscarPeloNome() {
        String nome = "Hamburguer de Frango";
        ProdutoEntity produtoEntity = new ProdutoEntity(Categoria.Lanche, nome, "Hamburguer delicioso",BigDecimal.valueOf(25.99) );
        when(springProdutoRepository.findByNome(nome)).thenReturn(produtoEntity);

        Produto result = produtoRepository.buscarPeloNome(nome);
        assertEquals(nome, result.getNome());
    }

    @Test
    public void testSalvar() {
        Produto produto = new Produto(Categoria.Lanche, "Hamburguer de Frango", "Hamburguer delicioso",BigDecimal.valueOf(25.99) );
        produtoRepository.salvar(produto);
        
    }

}
