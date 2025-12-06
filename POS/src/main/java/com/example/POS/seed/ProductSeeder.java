package com.example.POS.seed;

import com.example.POS.Model.Product;
import com.example.POS.Model.ProductImage;
import com.example.POS.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        if (productRepository.count() == 0) {

            List<Product> demoProducts = List.of(
                    new Product(null, "Apple iPhone 15", "Smart Phone", "Electronics",
                            799.00, 4.8, "Amazon", 5,
                            List.of("/products/1.png")),

                    new Product(null, "Samsung S24 Ultra", "Android Flagship Phone", "Electronics",
                            999.00, 4.9, "Samsung Store", 10,
                            List.of( "/products/2.jpg")),

                    new Product(null, "Dell XPS 13", "Ultrabook Laptop", "Computers",
                            1299.00, 4.7, "Dell Store", 7,
                            List.of( "/products/3.jpg")),

                    new Product(null, "Sony WH-1000XM5", "Noise Cancelling Headphones", "Audio",
                            349.00, 4.6, "Sony", 15,
                            List.of( "/products/4.jpg")),

                    new Product(null, "Apple Watch Series 9", "Smart Watch", "Wearables",
                            399.00, 4.5, "Apple Store", 8,
                            List.of( "/products/5.jpg")),

                    new Product(null, "Logitech MX Master 3S", "Premium Mouse", "Accessories",
                            99.00, 4.8, "Logitech", 20,
                            List.of( "/products/6.jpg")),

                    new Product(null, "LG OLED C3 55-inch", "4K Smart TV", "Television",
                            1499.00, 4.9, "LG Store", 3,
                            List.of( "/products/7.jpg")),

                    new Product(null, "Canon EOS R6", "Mirrorless Camera", "Cameras",
                            1999.00, 4.9, "Canon Store", 4,
                            List.of( "/products/8.jpg"))
            );



            productRepository.saveAll(demoProducts);

            System.out.println("✔ Demo Products Seeded Successfully!");
        }

        else {
            System.out.println("Products Already Exist!");
        }
    }
}
