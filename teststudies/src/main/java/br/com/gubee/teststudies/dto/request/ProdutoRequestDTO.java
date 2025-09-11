package br.com.gubee.teststudies.dto.request;


public record ProdutoRequestDTO(
        String nome,
        Double preco,
        Integer quantidade
) {}