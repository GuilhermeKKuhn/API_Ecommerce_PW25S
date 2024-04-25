package com.example.Ecommerce.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PedidoDto {

    @Id
    private Long id;

    @NotNull
    private LocalDateTime data;

    @ManyToOne
    private UsuarioDto usuario;

    @ManyToOne
    private List<ItensDoPedidoDto> itensPedido;

}