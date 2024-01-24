package com.fiap.lanchonete.infrastructure.controller.requestsdto;

import java.math.BigDecimal;

import com.fiap.lanchonete.domain.entity.Categoria;

public record ProdutoRequest(Categoria categoria,String nome,String descricao,BigDecimal valor) {}
