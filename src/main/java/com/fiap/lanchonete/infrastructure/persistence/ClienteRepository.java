package com.fiap.lanchonete.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.lanchonete.infrastructure.persistence.entity.ClienteEntity;


@Repository

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

	ClienteEntity findByNome(String nome);
	
	ClienteEntity findByCpf(String cpf);
}
