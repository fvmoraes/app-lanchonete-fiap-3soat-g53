package com.fiap.lanchonete.domain.entity;


public record Pedido(Integer idPedido, String nomeLanche, String nomeAcompanhamento, String nomeBebida,String nomeSobremesa,
 StatusPedido statusPedido, StatusPagamento statusPagamento) {

}
