package com.example.springboot_exercises.service;

import com.example.springboot_exercises.model.Product;
import com.example.springboot_exercises.model.dto.ProductRequestDTO;
import com.example.springboot_exercises.model.dto.ProductResponseDTO;
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

    public ProductResponseDTO addProduct(ProductRequestDTO p) { return toResponseDTO(repo.save(toEntity(p))); }

    public boolean deleteById(int id) { return repo.deleteById(id); }

    public ProductResponseDTO updateProduct(int id, ProductRequestDTO productDTO) {
        Optional<Product> existing = repo.findById(id);

        if(existing.isPresent()){
            existing.get().setName(productDTO.getName());
            existing.get().setPrice(productDTO.getPrice());
            existing.get().setInternalStatus(productDTO.getInternalStatus());
            return toResponseDTO(existing.get());
        }
        return null;
    }

    public Product patchProduct(int id, Product product){
        Optional<Product> existing = repo.findById(id);
        if (existing.isPresent()){
            if(product.getName() != null){

            }
            if(product.getPrice() != 0.0){

            }
            Product p = existing.get();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            return  p;
        }
        return null;
    }

    private ProductResponseDTO toResponseDTO(Product product){
        return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getCategory());
    }

    private Product toEntity(ProductRequestDTO request){
        Product product = new Product();

        if(!request.getName().isBlank()){
            product.setName(request.getName());
        }
        if(request.getPrice() != 0.0){
            product.setPrice(request.getPrice());
        }
        if(!request.getCategory().isBlank()){
            product.setCategory(request.getCategory());
        }
        if(!request.getInternalStatus().isBlank()){
            product.setInternalStatus(request.getInternalStatus());
        }
        return product;
    }

}
