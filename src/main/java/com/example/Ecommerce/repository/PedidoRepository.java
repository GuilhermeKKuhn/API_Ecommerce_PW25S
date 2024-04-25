package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.Pedido;
import com.example.Ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public List<Pedido> findByUsuario(Usuario usuario);

    Pedido findByIdAndUsuario(long id,Usuario usuario);

}
