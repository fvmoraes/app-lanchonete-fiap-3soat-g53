package com.fiap.lanchonete.infraestrutura.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.lanchonete.dominio.Categoria;
import com.fiap.lanchonete.infraestrutura.entidades.ProdutoEntity;

@Repository
public interface SpringProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {

	ProdutoEntity findByNome(String nome);
	
	List<ProdutoEntity> findByCategoria(Categoria categoria);
	
}
