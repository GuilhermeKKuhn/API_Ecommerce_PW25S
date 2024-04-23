package com.example.Ecommerce;


import com.example.Ecommerce.model.Produto;
import com.example.Ecommerce.model.Usuario;
import com.example.Ecommerce.repository.ProdutoRepository;
import com.example.Ecommerce.repository.UsuarioRepository;
import org.assertj.core.api.AssertionsForClassTypes;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev-postgres")
public class TestController {

    private final String API_USUARIOS = "/usuarios";

    private final String API_PRODUTOS = "/produtos";


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void cleanup() {
        usuarioRepository.deleteAll();
        produtoRepository.deleteAll();
        testRestTemplate.getRestTemplate().getInterceptors().clear();
    }

    @Test
    @DisplayName("Quando o usuário for válido, deve retornar 201 Created")
    public void postUsuario_seValido_deveRetornar201Created() {
        ResponseEntity<Object> response =
                testRestTemplate.postForEntity(
                        API_USUARIOS,
                        criaUsuarioValido(),
                        Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void postUsuario_quandoHouverUsuarioNulo_deveRetornar400BadRequest() {
        Usuario usuario = criaUsuarioValido();
        usuario.setUsername(null);
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_USUARIOS, usuario, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void postProduto_quandoHouverProdutoNulo_deveRetornar400BadRequest() {
        Produto produto = criaProdutoValido();
        produto.setNome(null);
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_PRODUTOS, produto, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void postUsuario_quandoHouverSenhaNula_deveRetornar400BadRequest() {
        Usuario usuario = criaUsuarioValido();
        usuario.setPassword(null);
        ResponseEntity<Object> response =
                testRestTemplate.postForEntity(API_USUARIOS, usuario, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @DisplayName("Post a new User When the User is valid receive a 200 Ok")
    public void postUser_whenUserIsValid_receiveOk(){

        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_USUARIOS, criaUsuarioValido(), Object.class);

        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Quando o produto for válido, deve retornar 201 Created")
    public void postProduto_seValido_deveRetornar201Created() {
        Produto produto = criaProdutoValido();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_PRODUTOS, produto, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }



    private Usuario criaUsuarioValido() {
        return Usuario.builder()
                .username("teste usuario")
                .password("P4ssword").build();
    }

    private Produto criaProdutoValido() {
        return Produto.builder()
                .nome("teste produto")
                .descricao("eletrodomestico")
                .preco(BigDecimal.valueOf(10.00))
                .urlImage("teste")
                .build();
    }
}
