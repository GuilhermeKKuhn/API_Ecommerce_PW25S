package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Categoria;
import com.example.Ecommerce.model.Produto;
import com.example.Ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

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

    public List<Produto> findByCategoria(Categoria categoria) {
        return this.produtoRepository.findByCategoria(categoria);
    }

    public Produto update(Produto produto) {
        Produto produtoaux = this.findById(produto.getId());
        produtoaux.setNome(produto.getNome());
        produtoaux.setDescricao(produto.getDescricao());
        produtoaux.setPreco(produto.getPreco());
        produtoaux.setCategoria(produto.getCategoria());
        return this.produtoRepository.save(produtoaux);
    }

    public List<Produto> findByNome(String nome) {
        return this.produtoRepository.findByNome(nome);
    }

    public Page<Produto> getProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }
}
