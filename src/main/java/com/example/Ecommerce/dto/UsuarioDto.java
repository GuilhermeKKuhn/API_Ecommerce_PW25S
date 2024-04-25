package com.example.Ecommerce.dto;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    @Id
    private Long id;

    @NotNull
    @Size(min = 4, max = 255)
    private String username;

}