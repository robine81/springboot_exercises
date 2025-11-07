package com.example.springboot_exercises.service;

import com.example.springboot_exercises.model.Product;
import com.example.springboot_exercises.model.dto.ProductRequestDTO;
import com.example.springboot_exercises.model.dto.ProductResponseDTO;
import com.example.springboot_exercises.model.dto.ProductResponseDTOAdmin;
import com.example.springboot_exercises.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<ProductResponseDTO> getAll() {
        List<Product> products = repo.findAll();
        List<ProductResponseDTO> productsRespDTO = new ArrayList<>();

        for(Product p : products){
            productsRespDTO.add(toResponseDTO(p));
        }
        return productsRespDTO;
    }

    public List<ProductResponseDTOAdmin> getAllAdmin() {
        List<Product> products = repo.findAll();
        List<ProductResponseDTOAdmin> productsRespDTO = new ArrayList<>();

        for(Product p : products){
            productsRespDTO.add(toResponseDTOAdmin(p));
        }
        return productsRespDTO;
    }

    public Optional<Product> getById(int id) { return repo.findById(id); }

    public List<ProductResponseDTO> searchByName(String name) {
        List<Product> products = repo.findByName(name);
        List<ProductResponseDTO> productsRespDTO = new ArrayList<>();

        for(Product p : products){
            productsRespDTO.add(toResponseDTO(p));
        }
        return productsRespDTO;
    }

    public List<ProductResponseDTO> searchByPrice(int price) {
        List<Product> products = repo.findByPrice(price);
        List<ProductResponseDTO> productsRespDTO = new ArrayList<>();

        for (Product p : products) {
            productsRespDTO.add(toResponseDTO(p));
        }
        return productsRespDTO;
    }

    public List<ProductResponseDTO> searchByStatus(String status) {
        List<Product> products = repo.findByStatus(status);
        List<ProductResponseDTO> productsRespDTO = new ArrayList<>();

        for (Product p : products) {
            productsRespDTO.add(toResponseDTO(p));
        }
        return productsRespDTO;
    }

    public List<ProductResponseDTO> searchByCategory(String category) {
        List<Product> products = repo.findByCategory(category);
        List<ProductResponseDTO> productsRespDTO = new ArrayList<>();

        for (Product p : products) {
            productsRespDTO.add(toResponseDTO(p));
        }
        return productsRespDTO;
    }

    public List<ProductResponseDTO> searchByNameAndPriceAndStatus(String name, int price, String status) {
        List<Product> products = repo.findByNameAndPriceAndStatus(name, price, status);
        List<ProductResponseDTO> productsRespDTO = new ArrayList<>();

        for(Product p : products){
            productsRespDTO.add(toResponseDTO(p));
        }
        return productsRespDTO;
    }

    public List<ProductResponseDTO> searchByNameAndPrice(String name, int price) {
        List<Product> products = repo.findByNameAndPrice(name, price);
        List<ProductResponseDTO> productsRespDTO = new ArrayList<>();

        for(Product p : products){
            productsRespDTO.add(toResponseDTO(p));
        }
        return productsRespDTO;
    }

    public List<ProductResponseDTO> searchByNameAndStatus(String name, String status) {
        List<Product> products = repo.findByNameAndStatus(name, status);
        List<ProductResponseDTO> productsRespDTO = new ArrayList<>();

        for(Product p : products){
            productsRespDTO.add(toResponseDTO(p));
        }
        return productsRespDTO;
    }

    public List<ProductResponseDTO> searchByPriceAndStatus(int price, String status) {
        List<Product> products = repo.findByPriceAndStatus(price, status);
        List<ProductResponseDTO> productsRespDTO = new ArrayList<>();

        for(Product p : products){
            productsRespDTO.add(toResponseDTO(p));
        }
        return productsRespDTO;
    }

    public ProductResponseDTO addProduct(ProductRequestDTO p) { return toResponseDTO(repo.save(toEntity(p))); }

    public boolean deleteById(int id) { return repo.deleteById(id); }

    public ProductResponseDTO updateProduct(int id, ProductRequestDTO productDTO) {
        Optional<Product> existing = repo.findById(id);

        if(existing.isPresent()){
            existing.get().setName(productDTO.getName());
            existing.get().setPrice(productDTO.getPrice());
            existing.get().setCategory(productDTO.getCategory());
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
        return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getCreatedDate());
    }

    private ProductResponseDTOAdmin toResponseDTOAdmin(Product product){
        return new ProductResponseDTOAdmin(product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getCreatedDate(), product.getInternalStatus());
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
        product.setCreatedDate(request.getCreatedDate());

        return product;
    }

}
