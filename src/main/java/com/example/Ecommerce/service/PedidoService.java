package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.PedidoDto;
import com.example.Ecommerce.model.ItensDoPedido;
import com.example.Ecommerce.model.Pedido;
import com.example.Ecommerce.model.Produto;
import com.example.Ecommerce.model.Usuario;
import com.example.Ecommerce.repository.PedidoRepository;
import com.example.Ecommerce.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;
    private final ProdutoRepository produtoRepository;

    private final ModelMapper modelMapper;

    private PedidoDto convertToDto(Pedido pedido) {
        return modelMapper.map(pedido, PedidoDto.class);
    }


    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, UsuarioService usuarioService, ProdutoRepository produtoRepository, ModelMapper modelMapper) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioService = usuarioService;
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
    }

    public PedidoDto findOne(Long id) {
        Pedido pedido = pedidoRepository.findByIdAndUsuario(id, usuarioService.getUserLogado());
        if (pedido != null) {
            return convertToDto(pedido);
        }
        return null;
    }

    public Pedido save(Pedido pedido) {
        Usuario usuario =  usuarioService.getUserLogado();
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado ou não logado.");
        }

        pedido.setUsuario(usuario);

        if (pedido.getItensPedido() != null){
            for (ItensDoPedido itens : pedido.getItensPedido()) {

                    Produto produto = produtoRepository.findById(itens.getProduto().getId()).
                            orElseThrow(() -> new RuntimeException("Produto com ID " + itens.getProduto().getId() + " não encontrado"));
                    itens.setProduto(produto);

                    if (itens.getPreco() == null && produto.getPreco() != null && itens.getQuantidade() != null) {
                        BigDecimal precoTotal = produto.getPreco().multiply(BigDecimal.valueOf(itens.getQuantidade()));
                        itens.setPreco(precoTotal);
                    }

                    itens.setPedido(pedido);
            }
        }
        return pedidoRepository.save(pedido);
    }

    public List<PedidoDto> findPedidos() {
        return pedidoRepository.findByUsuario(usuarioService.getUserLogado()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
