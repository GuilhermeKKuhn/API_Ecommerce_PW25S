package com.example.Ecommerce.Controller;

import com.example.Ecommerce.model.Categoria;
import com.example.Ecommerce.model.Produto;
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

    @DeleteMapping("/{id}1")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //@GetMapping("/categoriaId/{id}")
    //public ResponseEntity<List<Produto>> findByCategoria(@RequestParam Long categoriaId) {
    //    List<Produto> produtos = produtoService.findProdutosByCategoriaId(categoriaId);
    //    return ResponseEntity.ok().body(produtos);
    //}



}
