package com.example.springboot_exercises.repository;

import com.example.springboot_exercises.model.Product;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public ProductRepository(){
        products.add(new Product(1,"iPhone",200.0, "Available"));
        products.add(new Product(2,"Samsung",150.0, "Out of stock"));
        products.add(new Product(3,"Huawei",70.0, "Available"));
        //more fields:
        // products.add(new Product(4,"Huawei",70.0, "Available", LocalDate.of(2024, 10, 5), "Electronics"));
    }

    public List<Product> findAll() { return products; }

    public Optional<Product> findById(int id){
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public List<Product> findByName(String name){
        return products.stream()
                .filter(p -> p.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }

    public Product save(Product product){
        int nextId = products.stream()
                .mapToInt(p -> p.getId())
                .max()
                .orElse(0) +1;
        product.setId(nextId);
        products.add(product);
        return product;
    }

    public boolean deleteById(int id){
        return products.removeIf(p -> p.getId() == id );
    }
}
