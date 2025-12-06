package com.example.POS.Services;

import com.example.POS.Model.Product;
import com.example.POS.Repository.ProductRepository;
import com.example.POS.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
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

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not Found"));
    }

    public List<Product> searchProducts(String category , Double minPrice , Double maxPrice , String Keyword , Double ratings){

        Specification<Product> specification = Specification.allOf(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.priceBetween(minPrice, maxPrice))
                .and(ProductSpecification.hasNameOrDescriptionLike(Keyword))
                .and(ProductSpecification.ratingsGraterThan(ratings));
        return productRepository.findAll(specification);

    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }
}
