package com.example.POS.Services;

import com.example.POS.DTO.ProductDTO;
import com.example.POS.DTO.ProductImageDTO;
import com.example.POS.DTO.ProductReviewDto;
import com.example.POS.Model.Product;
import com.example.POS.Model.ProductReview;
import com.example.POS.Repository.ProductRepository;
import com.example.POS.Repository.ProductReviewRepository;
import com.example.POS.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//public class ProductService {
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private ProductReviewRepository productReviewRepository;
//
//
////    public List <Product> getAllProducts(){
////        List <Product> products = productRepository.findAll();
////        return products;
////    }
////Pagination
//    public Map <String , Object> getAllProducts(int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Product> products = productRepository.findAll(pageable);
//        List<ProductDTO> productDTOS = products.stream().map(this::convertToDTO).collect(Collectors.toList());
//        Map<String, Object> response = new HashMap<>();
//        response.put("products", productDTOS);
//        response.put("total", products.getTotalElements());
//
//
//        return response;
//    }
//
//    public ProductDTO convertToDTO(Product product){
//        ProductDTO  productDTO = new ProductDTO();
//        productDTO.setId(product.getId());
//        productDTO.setName(product.getName());
//        productDTO.setDescription(product.getDescription());
//        productDTO.setCategory(product.getCategory());
//        productDTO.setPrice(product.getPrice());
//        productDTO.setRatings(product.getRatings());
//        productDTO.setSeller(product.getSeller());
//        productDTO.setStock(product.getStock());
//        productDTO.setNumOfReviews(product.getNumOfReviews());
//
//
//        List <ProductReviewDto> reviewsDTO = product.getReviews().stream().map(review->{
//            ProductReviewDto reviewDTO = new ProductReviewDto();
//            reviewDTO.setProductId(review.getId());
//            reviewDTO.setComment(review.getComment());
//            reviewDTO.setRating(review.getRating());
//            return  reviewDTO;
//        }).collect(Collectors.toList());
//        productDTO.setReviews(reviewsDTO);
//
//        return productDTO;
//
//
//    }
//
//    public Product getProductById(Long id){
//        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not Found"));
//    }
//
//    public List<Product> searchProducts(String category , Double minPrice , Double maxPrice , String Keyword , Double ratings){
//
//        Specification<Product> specification = Specification.allOf(ProductSpecification.hasCategory(category))
//                .and(ProductSpecification.priceBetween(minPrice, maxPrice))
//                .and(ProductSpecification.hasNameOrDescriptionLike(Keyword))
//                .and(ProductSpecification.ratingsGraterThan(ratings));
//        return productRepository.findAll(specification);
//
//    }
//    @PostMapping
//    public Product createProduct(Product product){
//        return productRepository.save(product);
//    }
//
//
//    @PutMapping
//    public void addReviewToProduct(ProductReviewDto reviewDto){
//
//        Product product = productRepository.findById(reviewDto.getProductId()).orElseThrow(()-> new RuntimeException("Product Not Found"));
//
//        ProductReview productReview = new ProductReview();
//        productReview.setComment(reviewDto.getComment());
//        productReview.setRating(reviewDto.getRating());
//        productReview.setProduct(product);
//        productReviewRepository.save(productReview);
//    }
//
//
//}


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;


    // PAGINATION
    public Map<String , Object> getAllProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);

        List<ProductDTO> productDTOS =
                products.stream().map(this::convertToDTO).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("products", productDTOS);
        response.put("total", products.getTotalElements());

        return response;
    }


    // ENTITY -> DTO
//    public ProductDTO convertToDTO(Product product){
//        ProductDTO dto = new ProductDTO();
//
//        dto.setId(product.getId());
//        dto.setName(product.getName());
//        dto.setDescription(product.getDescription());
//        dto.setCategory(product.getCategory());
//        dto.setPrice(product.getPrice());
//        dto.setRatings(product.getRatings());
//        dto.setSeller(product.getSeller());
//        dto.setStock(product.getStock());
//        dto.setNumOfReviews(product.getNumOfReviews());
//        dto.setImages(product.getImages());
//
//        // MAPPING REVIEWS
//        List<ProductReviewDto> reviewDTOs = product.getReviews()
//                .stream()
//                .map(r -> new ProductReviewDto(
//                        r.getProduct().getId(),  // FIXED
//                        r.getRating(),
//                        r.getComment()
//                ))
//                .collect(Collectors.toList());
//
//        dto.setReviews(reviewDTOs);
//
//        return dto;
//
//        //Image
//
//        List<ProductImageDTO> productImageDTOS = product.getImages().stream()
//                .map(image -> new ProductImageDTO(image.getUrl()))
//                .collect(Collectors.toList());
//        dto.setImages(productImageDTOS);
//        return dto;
//
//
//
//
//
//
//        return dto;
//    }

//    public ProductDTO convertToDTO(Product product) {
//        ProductDTO dto = new ProductDTO();
//
//        dto.setId(product.getId());
//        dto.setName(product.getName());
//        dto.setDescription(product.getDescription());
//        dto.setCategory(product.getCategory());
//        dto.setPrice(product.getPrice());
//        dto.setRatings(product.getRatings());
//        dto.setSeller(product.getSeller());
//        dto.setStock(product.getStock());
//        dto.setNumOfReviews(product.getNumOfReviews());
//
//        // MAPPING REVIEWS
//        List<ProductReviewDto> reviewDTOs = product.getReviews()
//                .stream()
//                .map(r -> new ProductReviewDto(
//                        r.getProduct().getId(),
//                        r.getRating(),
//                        r.getComment()
//                ))
//                .collect(Collectors.toList());
//        dto.setReviews(reviewDTOs);
//
//        // MAPPING IMAGES
////        List<ProductImageDTO> imageDTOs = product.getImages()
////                .stream()
////                .map(img -> new ProductImageDTO(img.getPublicId()))
////                .collect(Collectors.toList());
////        dto.setImages(imageDTOs);
//
//        List<ProductImageDTO> imageDTOs = product.getImages()
//                .stream()
//                .map(img -> new ProductImageDTO(img.getUrl())) // return REAL URL
//                .collect(Collectors.toList());
//        dto.setImages(imageDTOs);
//
//
//        return dto;
//    }
    
    
    public ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setRatings(product.getRatings());
        dto.setSeller(product.getSeller());
        dto.setStock(product.getStock());
        dto.setNumOfReviews(product.getNumOfReviews());

        // ✅ SAFE REVIEW MAPPING
        List<ProductReviewDto> reviewDTOs =
                product.getReviews() == null
                        ? List.of()
                        : product.getReviews().stream()
                            .map(r -> new ProductReviewDto(
                                    product.getId(),   // ✅ FIXED
                                    r.getRating(),
                                    r.getComment()
                            ))
                            .collect(Collectors.toList());

        dto.setReviews(reviewDTOs);

        // ✅ SAFE IMAGE MAPPING
        List<ProductImageDTO> imageDTOs =
                product.getImages() == null
                        ? List.of()
                        : product.getImages().stream()
                            .map(img -> new ProductImageDTO(img.getUrl()))
                            .collect(Collectors.toList());

        dto.setImages(imageDTOs);

        return dto;
    }




    // GET PRODUCT BY ID
    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
    }


    // SEARCH
    public List<Product> searchProducts(String category, Double minPrice, Double maxPrice,
                                        String keyword, Double ratings){

        Specification<Product> specification =
                Specification
                        .allOf(ProductSpecification.hasCategory(category))
                        .and(ProductSpecification.priceBetween(minPrice, maxPrice))
                        .and(ProductSpecification.hasNameOrDescriptionLike(keyword))
                        .and(ProductSpecification.ratingsGraterThan(ratings));

        return productRepository.findAll(specification);
    }


    // ADD NEW PRODUCT
    public Product createProduct(Product product){
        return productRepository.save(product);
    }


    // ADD REVIEW TO PRODUCT + UPDATE RATING
    public void addReviewToProduct(ProductReviewDto reviewDto){

        Product product = productRepository.findById(reviewDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        ProductReview review = new ProductReview();
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setProduct(product);

        productReviewRepository.save(review);

        // UPDATE rating & numOfReviews
        updateProductRating(product);
    }


    // UPDATE PRODUCT RATING
    private void updateProductRating(Product product){

        List<ProductReview> reviews = productReviewRepository.findAll()
                .stream()
                .filter(r -> r.getProduct().getId().equals(product.getId()))
                .collect(Collectors.toList());

        int totalReviews = reviews.size();
        double avgRating = reviews.stream()
                .mapToDouble(ProductReview::getRating)
                .average()
                .orElse(0.0);

        product.setNumOfReviews(totalReviews);
        product.setRatings(avgRating);

        productRepository.save(product);
    }
}
