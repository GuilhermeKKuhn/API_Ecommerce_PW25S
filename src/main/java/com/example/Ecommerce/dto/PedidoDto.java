package com.example.Ecommerce.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

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
}