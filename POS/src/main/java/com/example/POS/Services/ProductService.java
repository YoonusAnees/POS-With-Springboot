package com.example.POS.Services;

import com.example.POS.Model.Product;
import com.example.POS.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List <Product> getAllProducts(){
        List <Product> products = productRepository.findAll();
        return products;
    }
}
