package com.example.Ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime data;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido")
    private List<ItensDoPedido> itensPedido = new ArrayList<>();
}
