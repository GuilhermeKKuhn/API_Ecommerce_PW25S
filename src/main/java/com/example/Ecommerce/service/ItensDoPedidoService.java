package com.example.Ecommerce.service;

import com.example.Ecommerce.model.ItensDoPedido;
import com.example.Ecommerce.model.Pedido;
import com.example.Ecommerce.repository.ItensDoPedidoRepository;
import com.example.Ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItensDoPedidoService {

    @Autowired
    private ItensDoPedidoRepository itensDoPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<ItensDoPedido> findByPedido(Pedido pedido) {
        pedidoRepository.findById(pedido.getId());
        try {
            return itensDoPedidoRepository.findByPedido(pedido);
        }catch (Exception e){
            throw new RuntimeException("Pedido não encontrado");
        }
    }

}
