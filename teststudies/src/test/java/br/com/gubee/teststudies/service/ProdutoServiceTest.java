package br.com.gubee.teststudies.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gubee.teststudies.dto.request.ProdutoRequestDTO;
import br.com.gubee.teststudies.dto.response.ProdutoResponseDTO;
import br.com.gubee.teststudies.entity.Produto;
import br.com.gubee.teststudies.repository.ProdutoRepository;

// Mock studies
@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    ProdutoRepository produtoRepository; // Apesar de ter sido criado, o repository so conhece a interface, mas não faz
                                         // ideia de como eh a implementacao, entao sempre que chamarmos algo, ele
                                         // retorna null

    @InjectMocks
    private ProdutoService produtoService; // Aqui, ele sabe como o codigo se comporta

    private Produto produto;

    @BeforeEach
    void setup() {
        produto = new Produto(1L, "Notebook", 3500.0, 5);
    }

    @Test
    void salvarProdutoTest() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoResponseDTO response = produtoService.salvar(new ProdutoRequestDTO("Notebook", 3500.0, 5));

        assertNotNull(response);
        assertEquals("Notebook", response.nome());
        assertEquals(3500.0, response.preco());
        assertEquals(5, response.quantidade());
    }

    @Test
    void listarTodosProdutosTest() {
        Produto produto2 = new Produto(2L, "Churrasqueira Controle Remoto", 150.0, 10);
        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto, produto2));

        List<ProdutoResponseDTO> lista = produtoService.listarTodos();

        assertEquals(2, lista.size());
        assertEquals("Notebook", lista.get(0).nome());
        assertEquals("Churrasqueira Controle Remoto", lista.get(1).nome());
    }

    @Test
    void buscarPorIdExistenteTest() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<ProdutoResponseDTO> response = produtoService.buscarPorId(1L);

        assertTrue(response.isPresent());
        assertEquals("Notebook", response.get().nome());
    }

    @Test
    void buscarPorIdInexistenteTest() {
        assertThrows(RuntimeException.class,
                () -> produtoService.buscarPorId(99L));
    }

    @Test
    void atualizarProdutoExistenteTest() {
        Produto atualizado = new Produto(1L, "Carro Gamer", 1000.0, 3);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(atualizado);

        ProdutoResponseDTO response = produtoService.atualizar(1L, new ProdutoRequestDTO("Carro Gamer", 1000.0, 3));

        assertEquals("Carro Gamer", response.nome());
        assertEquals(1000.0, response.preco());
        assertEquals(3, response.quantidade());
    }

    @Test
    void atualizarProdutoInexistenteTest() {
        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> produtoService.atualizar(99L, new ProdutoRequestDTO("Nada de Nada", 100.0, 1)));

    }

    @Test
    void deletarProdutoTest() {
        doNothing().when(produtoRepository).deleteById(1L);

        produtoService.deletar(1L);
    }
}
