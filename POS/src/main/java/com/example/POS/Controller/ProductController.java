package com.example.POS.Controller;

import com.example.POS.DTO.ProductDTO;
import com.example.POS.Model.Product;
import com.example.POS.Model.ProductImage;
import com.example.POS.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

   @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
   }

//   public ResponseEntity<Product> addProduct(@RequestBody Product product){
//       Product saved = productService.createProduct(product);
//       return ResponseEntity.ok(saved);
//   }

   @PostMapping()
   public ResponseEntity<?> addProduct(@RequestBody ProductDTO dto) {

       Product product = new Product();
       product.setName(dto.getName());
       product.setDescription(dto.getDescription());
       product.setCategory(dto.getCategory());
       product.setPrice(dto.getPrice());
       product.setSeller(dto.getSeller());
       product.setStock(dto.getStock());
       product.setRatings(0.0);

       // Convert image URL strings → ProductImage list
       if (dto.getImages() != null) {
           List<ProductImage> images = dto.getImages()
                   .stream()
                   .map(imgDto -> new ProductImage(imgDto.getUrl(), product))
                   .collect(Collectors.toList());
           product.setImages(images);
       }

       Product saved = productService.createProduct(product);
       return ResponseEntity.ok(saved);
   }


    @GetMapping("/search")
   public List<Product> searchProduct(@RequestParam (required = false) String category,@RequestParam (required = false) Double minPrice,@RequestParam (required = false) Double maxPrice,@RequestParam (required = false) String Keyword ,@RequestParam (required = false) Double ratings) {
        return productService.searchProducts(category,minPrice,maxPrice,Keyword,ratings);
   }

}
