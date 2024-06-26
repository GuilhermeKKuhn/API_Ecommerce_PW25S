package com.example.Ecommerce.Controller;

import com.example.Ecommerce.model.Usuario;
import com.example.Ecommerce.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping()
    public ResponseEntity<Void> save(@Valid @RequestBody Usuario usuario) {
        this.usuarioService.save(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> getCurrentUser() {
        Usuario currentUser = usuarioService.getUserLogado();
        return ResponseEntity.ok().body(currentUser);
    }


    // NAO TEM NECESSIDADE DE EXISTIR ESSES DOIS ENDPOINTS
    /*@PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        this.usuarioService.update(usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}
