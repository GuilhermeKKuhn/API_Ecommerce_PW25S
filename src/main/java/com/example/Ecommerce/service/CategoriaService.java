package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Categoria;
import com.example.Ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        Optional<Categoria> categoria = this.categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new RuntimeException(
                "Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()
        ));
    }

    public Categoria save(Categoria categoria) {
        categoria = categoriaRepository.save(categoria);
        return categoria;
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.categoriaRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel excluir pois está relacionado com outra entidade");
        }
    }

    public Categoria update(Categoria categoria) {
        Categoria categoriaAux = this.findById(categoria.getId());
        categoriaAux.setCategoria(categoria.getCategoria());
        return categoriaRepository.save(categoriaAux);
    }

}
