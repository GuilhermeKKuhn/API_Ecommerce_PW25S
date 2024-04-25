package com.example.Ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_itensDoPedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItensDoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tb_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "tb_produto")
    private Produto produto;

    private Integer quantidade;

    private BigDecimal preco;


}
