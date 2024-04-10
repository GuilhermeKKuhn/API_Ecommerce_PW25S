package com.example.Ecommerce.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String nome;

    @NotNull
    @Size(min = 2, max = 255)
    private String descricao;

    @NotNull
    @Digits(integer=10, fraction=2)
    private BigDecimal preco;

    @NotNull
    private String urlImage;

    @NotNull
    private Categoria categoriaId;
}
