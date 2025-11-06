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

    public List<Product> searchByPrice(int price) { return repo.findByPrice(price); }

    public List<Product> searchByStatus(String status) { return repo.findByStatus(status); }

    public List<Product> searchByCategory(String category) { return repo.findByCategory(category); }

    public List<Product> searchByNameAndPriceAndStatus(String name, int price, String status) { return repo.findByNameAndPriceAndStatus(name, price, status); }

    public List<Product> searchByNameAndPrice(String name, int price) { return repo.findByNameAndPrice(name, price); }

    public List<Product> searchByNameAndStatus(String name, String status) { return repo.findByNameAndStatus(name, status); }

    public List<Product> searchByPriceAndStatus(int price, String status) { return repo.findByPriceAndStatus(price, status); }

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
