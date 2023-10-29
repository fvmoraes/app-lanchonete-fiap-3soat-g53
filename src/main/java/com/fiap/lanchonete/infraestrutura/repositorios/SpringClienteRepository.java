package com.fiap.lanchonete.infraestrutura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.lanchonete.infraestrutura.entidades.ClienteEntity;

@Repository

public interface SpringClienteRepository extends JpaRepository<ClienteEntity, String> {

	ClienteEntity findByNome(String nome);
	
	ClienteEntity findByCpf(String cpf);
}
