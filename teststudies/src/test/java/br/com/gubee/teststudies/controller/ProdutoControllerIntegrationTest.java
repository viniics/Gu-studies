package br.com.gubee.teststudies.controller;

import br.com.gubee.teststudies.dto.request.ProdutoRequestDTO;
import br.com.gubee.teststudies.entity.Produto;
import br.com.gubee.teststudies.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        produtoRepository.deleteAll();
    }

    @Test
    void criarEListarProdutoTest() throws Exception {
        ProdutoRequestDTO request = new ProdutoRequestDTO("Notebook", 3500.0, 5);
        mockMvc.perform(post("/produtos") // Simula a requisicao pro endpoint.
                .contentType(MediaType.APPLICATION_JSON) //Aqui eh que nem o "raw" do postman
                .content(objectMapper.writeValueAsString(request))) // o text em json propriamnte 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Notebook"));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Notebook"))
                .andExpect(jsonPath("$[0].preco").value(3500.0))
                .andExpect(jsonPath("$[0].quantidade").value(5));
    }

    @Test
    void buscarProdutoPorIdTest() throws Exception {
        var produto = produtoRepository.save(
                new Produto(null, "Mouse", 150.0, 10)
        );

        mockMvc.perform(get("/produtos/" + produto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Mouse"))
                .andExpect(jsonPath("$.preco").value(150.0))
                .andExpect(jsonPath("$.quantidade").value(10));
    }

    @Test
    void atualizarProdutoTest() throws Exception {
        var produto = produtoRepository.save(
                new Produto(null, "Teclado", 200.0, 3)
        );

        ProdutoRequestDTO update = new ProdutoRequestDTO("Teclado Gamer", 300.0, 2);

        mockMvc.perform(put("/produtos/" + produto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Teclado Gamer"))
                .andExpect(jsonPath("$.preco").value(300.0))
                .andExpect(jsonPath("$.quantidade").value(2));
    }

    @Test
    void deletarProdutoTest() throws Exception {
        var produto = produtoRepository.save(
                new Produto(null, "Monitor", 1200.0, 1)
        );

        mockMvc.perform(delete("/produtos/" + produto.getId()))
                .andExpect(status().isNoContent());
        
      mockMvc.perform(get("/produtos/"+ produto.getId()))
       .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
       .andExpect(result -> assertEquals("Produto não encontrado",
                                       result.getResolvedException().getMessage()));

    }
}
