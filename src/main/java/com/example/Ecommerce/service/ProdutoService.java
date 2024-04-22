package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Categoria;
import com.example.Ecommerce.model.Produto;
import com.example.Ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        Optional<Produto> produto = this.produtoRepository.findById(id);
        return produto.orElseThrow(() -> new RuntimeException(
                "Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()
        ));
    }

    public Produto save(Produto produto) {
        produto = this.produtoRepository.save(produto);
        return produto;
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.produtoRepository.deleteById(id);
        }catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois está relacionado com outra entidade");
        }
    }

    public List<Produto> findProdutosByCategoriaId(Long categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId);
    }

    public Produto update(Produto produto) {
        Produto produtoaux = this.findById(produto.getId());
        produtoaux.setNome(produto.getNome());
        produtoaux.setDescricao(produto.getDescricao());
        produtoaux.setPreco(produto.getPreco());
        produtoaux.setCategoria(produto.getCategoria());
        return this.produtoRepository.save(produtoaux);
    }

    public Produto findByNome(String nome) {
        return this.produtoRepository.findByNome(nome);
    }
}
