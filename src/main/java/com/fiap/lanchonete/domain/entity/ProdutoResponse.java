package com.fiap.lanchonete.domain.entity;

import java.math.BigDecimal;

public record ProdutoResponse (Categoria categoria,String nome,String descricao,BigDecimal valor) {}