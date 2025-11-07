package com.example.springboot_exercises.model.dto;

import java.time.LocalDate;

public class ProductRequestDTOAdmin {
    private String name;
    private double price;
    private String category;
    private LocalDate createdDate;
    private String internalStatus;

    public ProductRequestDTOAdmin() {}

    public ProductRequestDTOAdmin(String name, double price, String category, LocalDate createdDate, String internalStatus) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.createdDate = createdDate;
        this.internalStatus = internalStatus;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getInternalStatus() {
        return internalStatus;
    }

    public void setInternalStatus(String internalStatus) {
        this.internalStatus = internalStatus;
    }
}
