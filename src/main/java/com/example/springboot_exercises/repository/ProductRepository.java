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
        products.add(new Product(1,"iPhone",200.0, "Available", "Electronics"));
        products.add(new Product(2,"Samsung",150.0, "Out_of_stock", "Electronics"));
        products.add(new Product(3,"Huawei",70.0, "Available", "Electronics"));
        products.add(new Product(4,"iPhone",1200.0, "Available", "Electronics"));
        products.add(new Product(5,"Android",50.0, "Out_of_stock", "Electronics"));
        products.add(new Product(6,"Huawei",70.0, "Available", "Electronics"));
        products.add(new Product(7,"Apple",6.0, "Out_of_stock", "Fruit"));
        products.add(new Product(8,"Pear",5.0, "Out_of_stock", "Fruit"));
        products.add(new Product(9,"Pineapple",7.0, "Available", "Fruit"));
        products.add(new Product(10,"Strawberry",16.0, "Out_of_stock", "Fruit"));
        // products.add(new Product(4,"Huawei",70.0, "Available", LocalDate.of(2024, 10, 5), "Electronics"));
    }

    public List<Product> findAll() { return products; }

    public Optional<Product> findById(int id){
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public List<Product> findByNameAndPriceAndStatus(String name, int maxPrice, String status){
        return products.stream()
                .filter(p -> p.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .filter(p -> p.getPrice() <= maxPrice)
                .filter(p -> p.getStatus()
                        .toLowerCase()
                        .contains(status.toLowerCase()))
                .toList();
    }

    public List<Product> findByNameAndPrice(String name, int maxPrice){
        return products.stream()
                .filter(p -> p.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .filter(p -> p.getPrice() <= maxPrice)
                .toList();
    }

    public List<Product> findByNameAndStatus(String name, String status){
        return products.stream()
                .filter(p -> p.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .filter(p -> p.getStatus()
                        .toLowerCase()
                        .contains(status.toLowerCase()))
                .toList();
    }

    public List<Product> findByPriceAndStatus(int maxPrice, String status){
        return products.stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .filter(p -> p.getStatus()
                        .toLowerCase()
                        .contains(status.toLowerCase()))
                .toList();
    }

    public List<Product> findByName(String name){
        return products.stream()
                .filter(p -> p.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }

    public List<Product> findByPrice(int maxPrice){
        return products.stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .toList();
    }

    public List<Product> findByStatus(String status){
        return products.stream()
                .filter(p -> p.getStatus()
                        .toLowerCase()
                        .contains(status.toLowerCase()))
                .toList();
    }

    public List<Product> findByCategory(String category){
        return products.stream()
                .filter(p -> p.getCategory()
                        .toLowerCase()
                        .contains(category.toLowerCase()))
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
