package com.fiap.lanchonete.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.lanchonete.domain.entity.StatusPedido;


public interface PedidoRepository  extends JpaRepository<PedidoEntity, Integer>{
	
	List<PedidoEntity> findAllByStatusPedidoOrderById(StatusPedido status);
}
