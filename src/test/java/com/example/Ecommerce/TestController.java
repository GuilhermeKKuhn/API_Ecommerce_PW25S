package com.example.Ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
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
    public void cleanup(){
        usuarioRepository.deleteAll();
        produtoRepository.deleteAll();
        testRestTemplate.getRestTemplate().getInterceptors().clear();
    }

    @Test
    @DisplayName("Quando o usuario for valido retorne um 200 ok")
    public void postUser_WhenUserIsValid_receivedOk(){
        ReponseEntity<Object> response =
            testRestTemplate.postForEntity(
                    API_USUARIOS,
                    criaUsuarioValido(),
                    Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
