package br.com.gubee.teststudies.dto.response;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        Double preco,
        Integer quantidade
) {}