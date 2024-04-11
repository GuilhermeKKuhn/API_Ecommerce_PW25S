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


    private Pedido pedidoId;
    private Produto produtoId;
    private Integer quantidade;
    private BigDecimal preco;

}
