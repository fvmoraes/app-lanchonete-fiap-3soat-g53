package com.fiap.lanchonete.infrastructure.controller;

import com.fiap.lanchonete.domain.entity.StatusPedido;

public record PedidoResponse(Integer idPedido, String nomeLanche, String nomeAcompanhamento, String nomeBebida, String nomeSobremesa,
		 StatusPedido statusPedido) {

}
