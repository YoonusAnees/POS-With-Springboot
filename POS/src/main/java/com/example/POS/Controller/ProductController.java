package com.example.POS.Controller;

import com.example.POS.Model.Product;
import com.example.POS.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping
//    public ResponseEntity<List<Product>> getAllProducts(){
//        List<Product> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }

    @GetMapping
   public Map<String , Object>getAllProducts(@RequestParam(defaultValue = "0")int page ,  @RequestParam(defaultValue = "5")int size){
       return productService.getAllProducts(page, size);
   }
}
