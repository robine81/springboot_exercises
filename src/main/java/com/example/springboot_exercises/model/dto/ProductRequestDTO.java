package com.example.springboot_exercises.model.dto;

import java.time.LocalDate;

public class ProductRequestDTO {
    private String name;
    private double price;
    private String category;
    private LocalDate createdDate;
    private String internalStatus;

    public ProductRequestDTO() {
        this.internalStatus = "";
        this.createdDate = LocalDate.now();
    }

    public ProductRequestDTO(String name, double price, String category, String internalStatus) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.internalStatus = internalStatus;
        this.createdDate = LocalDate.now();
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getInternalStatus() { return internalStatus; }

    public void setInternalStatus(String internalStatus) { this.internalStatus = internalStatus; }

    public LocalDate getCreatedDate() { return createdDate; }

    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    @Override
    public String toString() {
        return "ProductRequestDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", createdDate=" + createdDate +
                ", internalStatus='" + internalStatus + '\'' +
                '}';
    }
}
