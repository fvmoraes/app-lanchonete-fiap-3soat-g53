package com.fiap.lanchonete.infrastructure.persistence.converters;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.lanchonete.domain.entity.Produto;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProdutoListConverter implements AttributeConverter<List<Produto>, String> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(List<Produto> produtos) {
		try {
			return objectMapper.writeValueAsString(produtos);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Erro ao converter lista de produtos para JSON", e);
		}
	}

	@Override
	public List<Produto> convertToEntityAttribute(String json) {
		try {
			return objectMapper.readValue(json, new TypeReference<List<Produto>>() {
			});
		} catch (IOException e) {
			throw new IllegalArgumentException("Erro ao converter JSON para lista de produtos", e);
		}
	}
}
