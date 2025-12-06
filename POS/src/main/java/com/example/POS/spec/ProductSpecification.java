package com.example.POS.spec;

import com.example.POS.Model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
public static Specification<Product> hasCategory(String category){

    return ((root, query, criteriaBuilder) -> category==null? null: criteriaBuilder.equal(root.get("category"), category) );

}

public static Specification<Product> priceBetween(Double min, Double max){

    return (root, query, criteriaBuilder) -> {
        if (min==null || max==null) return null;
        if (min ==null && max ==null) return null;
        if (min==null) return criteriaBuilder.lessThanOrEqualTo(root.get("price"), max);
        if (max==null) return criteriaBuilder.lessThanOrEqualTo(root.get("price"), min);
        return criteriaBuilder.between(root.get("price"), min, max);
    };

}

public static Specification<Product> hasNameOrDescriptionLike(String Keyword){
    return (root, query, criteriaBuilder) -> {
        if (Keyword==null || Keyword.isEmpty()) return null;
        return criteriaBuilder.or(
                criteriaBuilder.like(root.get("name"), "%" +Keyword.toLowerCase() + "%"),
                criteriaBuilder.like(root.get("description"), "%" +Keyword.toLowerCase() + "%")

        );

    };
}

public static Specification<Product> ratingsGraterThan(Double ratings){
    return (root, query, criteriaBuilder) -> {
        if (ratings==null) return null;
        return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), ratings);
    };
}

}

