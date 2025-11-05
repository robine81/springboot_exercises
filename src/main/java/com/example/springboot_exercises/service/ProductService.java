package com.example.springboot_exercises.service;

import com.example.springboot_exercises.model.Product;
import com.example.springboot_exercises.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> getAll() { return repo.findAll(); }

    public Optional<Product> getById(int id) { return repo.findById(id); }

    public List<Product> searchByName(String name) { return repo.findByName(name); }

    public Product addProduct(Product p) { return repo.save(p); }

    public boolean deleteById(int id) { return repo.deleteById(id); }

    public Product updateProduct(int id, Product product) {
        Optional<Product> existing = repo.findById(id);

        if(existing.isPresent()){
            Product p = existing.get();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setStatus(product.getStatus());
            return p;
        }
        return null;
    }


}
