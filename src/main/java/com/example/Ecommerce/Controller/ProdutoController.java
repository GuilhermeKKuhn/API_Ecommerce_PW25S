package com.example.Ecommerce.Controller;

import com.example.Ecommerce.model.Categoria;
import com.example.Ecommerce.model.Produto;
import com.example.Ecommerce.service.CategoriaService;
import com.example.Ecommerce.service.ProdutoService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produtos = produtoService.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto) {
        this.produtoService.save(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("i{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        this.produtoService.update(produto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoriaId/{id}")
    public ResponseEntity<List<Produto>> findProdutosByCategoria(@PathVariable("id") Long categoriaId) {
        Categoria categoria = categoriaService.findById(categoriaId);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        List<Produto> produtos = produtoService.findByCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Produto>> findProdutosByNome(@RequestParam String nome) {
        List<Produto> produtos = this.produtoService.findByNome(nome);
        return ResponseEntity.ok(produtos);
    }




}
