package com.example.Ecommerce;

import com.example.Ecommerce.model.Usuario;
import com.example.Ecommerce.repository.UsuarioRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Configuration
@ActiveProfiles("dev-postgres")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerTest {

    private final String API_USUARIOS = "/usuario";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void cleanup() {
        usuarioRepository.deleteAll();
        testRestTemplate.getRestTemplate().getInterceptors().clear();
    }

    @Test
    public void postUser_whenUserIsValid_receiveOk() {
        Usuario usuario = new Usuario();
        usuario.setUsername("test-user");
        usuario.setPassword("P4ssword");


        ResponseEntity<Object> response = testRestTemplate.postForEntity("/usuario", usuario, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void postUsuario_quandoHouverUsuarioNulo_deveRetornar400BadRequest() {
        Usuario usuario = criaUsuarioValido();
        usuario.setUsername(null);
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_USUARIOS, usuario, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUsuario_quandoHouverSenhaNula_deveRetornar400BadRequest() {
        Usuario usuario = criaUsuarioValido();
        usuario.setPassword(null);
        ResponseEntity<Object> response =
                testRestTemplate.postForEntity(API_USUARIOS, usuario, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    private Usuario criaUsuarioValido() {
        return Usuario.builder()
                .username("test-user")
                .password("P4ssword").build();
    }

}