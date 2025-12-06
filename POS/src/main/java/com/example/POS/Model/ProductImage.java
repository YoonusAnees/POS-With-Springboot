package com.example.POS.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publicId;

    private String url;

    public ProductImage() {
    }

    public ProductImage(Long id, String publicId, String url) {
        this.id = id;
        this.publicId = publicId;
        this.url = url;
    }

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(String url , Product product) {
        this.url = "/uploads"+url;
        this.publicId = url;
        this.product = product;
    }


}

