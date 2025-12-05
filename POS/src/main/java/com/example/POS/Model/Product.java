package com.example.POS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
@AllArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Product Name Field is Required")
    private String name;

   @NotBlank(message = "Description Field is Required")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Price Filed is Required")
    @PositiveOrZero(message = "Value Must be Zero or Grater Than Zero")
    private double price;

    @NotNull(message = "Price Field is Required")
    private Double ratings =0.0;

    @NotBlank(message = "Seller Field is Required")
    private String seller;

    @NotNull(message = "Stock Field is Required")
    private Integer stock = 0;

    private Integer numOfReviews = 0;




}
