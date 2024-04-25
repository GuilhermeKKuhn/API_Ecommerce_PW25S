package com.example.Ecommerce.Controller;

import com.example.Ecommerce.dto.PedidoDto;
import com.example.Ecommerce.model.Pedido;
import com.example.Ecommerce.model.Usuario;
import com.example.Ecommerce.service.PedidoService;
import com.example.Ecommerce.service.UsuarioService;
import com.example.Ecommerce.shared.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    @Autowired
    public PedidoController(PedidoService pedidoService, UsuarioService usuarioService) {
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<GenericResponse> create(@RequestBody Pedido pedido) {
        Usuario usuarioLogado = usuarioService.getUserLogado();
        if (usuarioLogado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GenericResponse.builder().message("Usuário não encontrado ou não logado.").build());
        }
        pedido.setUsuario(usuarioLogado);
        pedidoService.save(pedido);
        return ResponseEntity.ok(GenericResponse.builder().message("Pedido salvo com sucesso.").build());
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoDto> findOne(@PathVariable Long id) {
        PedidoDto pedido = pedidoService.findOne(id, usuarioService);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.noContent().build();
    }

    @GetMapping("/meuspedidos")
    public ResponseEntity<List<PedidoDto>> findPedidos() {
        return ResponseEntity.ok(pedidoService.findPedidos(usuarioService));
    }


}
