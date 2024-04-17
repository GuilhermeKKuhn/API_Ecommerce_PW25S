package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Pedido;
import com.example.Ecommerce.model.Produto;
import com.example.Ecommerce.model.Usuario;
import com.example.Ecommerce.repository.PedidoRepository;
import com.example.Ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;


    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    public Pedido FindById(Long id) {
        Optional<Pedido> pedido = this.pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new RuntimeException(
                "Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()
        ));
    }

    public Pedido Save(Pedido pedido) {
        pedido = this.pedidoRepository.save(pedido);
        return pedido;
    }

    public void Delete(Long id) {
        FindById(id);
        try {
            this.pedidoRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel excluir pois está relacionado com outra entidade");
        }
    }

    public List<Pedido> findByUsuario(Usuario usuario) {
       List<Pedido> pedidos = this.pedidoRepository.findByUsuario(usuario);
       return pedidos;
    }
}
