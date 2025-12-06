package com.example.POS.seed;

import com.example.POS.Model.Product;
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
                    new Product(null, "Apple iPhone 15", "Smart Phone", 799.00, 4.8, "Amazon", 5),
                    new Product(null, "Samsung S24 Ultra", "Android Flagship Phone", 999.00, 4.9, "Samsung Store", 10),
                    new Product(null, "Dell XPS 13", "Ultrabook Laptop", 1299.00, 4.7, "Dell Store", 7),
                    new Product(null, "Sony WH-1000XM5", "Noise Cancelling Headphones", 349.00, 4.6, "Sony", 15),
                    new Product(null, "Apple Watch Series 9", "Smart Watch", 399.00, 4.5, "Apple Store", 8),
                    new Product(null, "Logitech MX Master 3S", "Premium Mouse", 99.00, 4.8, "Logitech", 20),
                    new Product(null, "LG OLED C3 55-inch", "4K Smart TV", 1499.00, 4.9, "LG Store", 3),
                    new Product(null, "Canon EOS R6", "Mirrorless Camera", 1999.00, 4.9, "Canon Store", 4)
            );

            productRepository.saveAll(demoProducts);

            System.out.println("✔ Demo Products Seeded Successfully!");
        }

        else {
            System.out.println("Products Already Exist!");
        }
    }
}
