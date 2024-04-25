package com.example.Ecommerce;

import com.example.Ecommerce.model.Categoria;
import com.example.Ecommerce.model.Produto;
import com.example.Ecommerce.model.Usuario;
import com.example.Ecommerce.repository.CategoriaRepository;
import com.example.Ecommerce.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev-postgres")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerTest {

    private final String API_PRODUTOS = "/produto";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @BeforeEach
    public void cleanup() {
        usuarioRepository.deleteAll();
        testRestTemplate.getRestTemplate().getInterceptors().clear();
    }

    @Test
    public void postUser_whenUserIsValid_receiveOk() {
        Produto produto = new Produto();
        Categoria categoria = new Categoria();

        produto.setNome("teste_produto");
        produto.setDescricao("teste_descricao");
        produto.setPreco(BigDecimal.valueOf(10.00));
        produto.setUrlImage("teste_url");

        categoria.setId(categoria.getId());
        produto.setCategoria(categoria);


        ResponseEntity<Object> response = testRestTemplate.postForEntity("/produto", produto, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Quando o produto for válido, deve retornar 201 Created")
    public void postProduto_seValido_deveRetornar201Created() {
        Produto produto = criaProdutoValido();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_PRODUTOS, produto, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private Produto criaProdutoValido() {
        return Produto.builder()
                .nome("teste produto")
                .descricao("teste_descricao")
                .preco(BigDecimal.valueOf(10.00))
                .urlImage("teste_url")
                .build();
    }

}