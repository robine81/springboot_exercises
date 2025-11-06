package com.example.springboot_exercises.model;

import com.example.springboot_exercises.model.dto.ProductRequestDTO;

import java.time.LocalDate;

public class Product {
    private int id;
    private String name;
    private double price;
    private String internalStatus;
    //private LocalDate createdDate;
    private String category;

    public Product() {}

    public Product(int id, String name, double price, String status, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.internalStatus = status;
        //this.createdDate = createdDate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInternalStatus() { return internalStatus; }

    public void setInternalStatus(String status) { this.internalStatus = status; }

    //public LocalDate getCreatedDate() {
    //    return createdDate;
    //}

    //public void setCreatedDate(LocalDate createdDate) {
    //    this.createdDate = createdDate;
    //}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status='" + internalStatus + '\'' +
                //", createdDate=" + createdDate +
                ", category='" + category + '\'' +
                '}';
    }
}
