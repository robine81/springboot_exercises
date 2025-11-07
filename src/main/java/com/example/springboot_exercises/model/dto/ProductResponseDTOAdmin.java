package com.example.springboot_exercises.model.dto;

import java.time.LocalDate;

public class ProductResponseDTOAdmin {
    private int id;
    private String name;
    private double price;
    private String category;
    private LocalDate createdDate;
    String internalStatus;

    public ProductResponseDTOAdmin() {}

    public ProductResponseDTOAdmin(int id, String name, double price, String category, LocalDate createdDate, String internalStatus) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.internalStatus = internalStatus;
        this.category = category;
        this.createdDate = createdDate;
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

    public String getInternalStatus() {
        return internalStatus;
    }

    public void setInternalStatus(String internalStatus) {
        this.internalStatus = internalStatus;
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

    @Override
    public String toString() {
        return "ProductResponseDTOAdmin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", createdDate=" + createdDate +
                ", internalStatus='" + internalStatus + '\'' +
                '}';
    }
}
