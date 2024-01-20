package com.fiap.lanchonete.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.infrastructure.persistence.entity.ProdutoEntity;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {

	ProdutoEntity findByNome(String nome);
	
	List<ProdutoEntity> findByCategoria(Categoria categoria);
	
}
