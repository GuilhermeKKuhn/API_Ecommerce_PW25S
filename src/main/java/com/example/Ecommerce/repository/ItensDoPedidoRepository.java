package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.ItensDoPedido;
import com.example.Ecommerce.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensDoPedidoRepository extends JpaRepository<ItensDoPedido, Long> {

    public List<ItensDoPedido> findByPedido(Pedido pedido);
}
