package br.com.gubee.teststudies.service;

import br.com.gubee.teststudies.dto.request.ProdutoRequestDTO;
import br.com.gubee.teststudies.dto.response.ProdutoResponseDTO;
import br.com.gubee.teststudies.entity.Produto;
import br.com.gubee.teststudies.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = new Produto(null, dto.nome(), dto.preco(), dto.quantidade());
        Produto salvo = repository.save(produto);
        return toResponseDTO(salvo);
    }

    public List<ProdutoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<ProdutoResponseDTO> buscarPorId(Long id) {
        return Optional.of(repository.findById(id).map(this::toResponseDTO).orElseThrow(() -> new RuntimeException("Produto não encontrado")));
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        return repository.findById(id)
                .map(produto -> {
                    produto.setNome(dto.nome());
                    produto.setPreco(dto.preco());
                    produto.setQuantidade(dto.quantidade());
                    Produto atualizado = repository.save(produto);
                    return toResponseDTO(atualizado);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private ProdutoResponseDTO toResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade()
        );
    }
}
