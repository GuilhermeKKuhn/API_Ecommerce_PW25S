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
    private Pedido pedido;

    @OneToOne
    private Produto produto;

    private Integer quantidade;

    private BigDecimal preco;

    public void CalcularPreco() {
        if (produto.getPreco() != null && quantidade != null) {

            preco = produto.getPreco().multiply(new BigDecimal(quantidade));
        }
    }

}
