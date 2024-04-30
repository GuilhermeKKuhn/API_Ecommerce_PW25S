package com.example.Ecommerce.dto;

import com.example.Ecommerce.model.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItensDoPedidoDto {

    @Id
    private Long id;

    @ManyToOne
    private Produto produto;

    private Integer quantidade;

    private BigDecimal preco;

}
