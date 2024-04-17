package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.Categoria;
import com.example.Ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findByCategoria(Categoria categoria);

    public Produto findByNome(String nome);
}
