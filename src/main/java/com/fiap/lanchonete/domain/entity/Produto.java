package com.fiap.lanchonete.domain.entity;

import java.math.BigDecimal;


public record Produto(Categoria categoria,String nome,String descricao,BigDecimal valor) {}
