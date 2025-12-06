package com.example.POS.Services;

import com.example.POS.Model.Product;
import com.example.POS.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


//    public List <Product> getAllProducts(){
//        List <Product> products = productRepository.findAll();
//        return products;
//    }
//Pagination
    public Map <String , Object> getAllProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("products", products.getContent());
        response.put("total", products.getTotalElements());


        return response;
    }
}
