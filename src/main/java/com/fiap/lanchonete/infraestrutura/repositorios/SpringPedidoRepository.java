package com.fiap.lanchonete.infraestrutura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.lanchonete.dominio.StatusPedido;
import com.fiap.lanchonete.infraestrutura.entidades.PedidoEntity;

public interface SpringPedidoRepository  extends JpaRepository<PedidoEntity, Integer>{
	
	List<PedidoEntity> findAllByStatusPedidoOrderById(StatusPedido status);

}
