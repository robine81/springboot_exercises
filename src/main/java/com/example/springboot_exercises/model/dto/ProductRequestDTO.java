package com.example.springboot_exercises.model.dto;

public class ProductRequestDTO {
    private String name;
    private double price;
    private String internalStatus;
    private String category;

    public ProductRequestDTO() {}

    public ProductRequestDTO(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.internalStatus = "";
    }

    public ProductRequestDTO(String name, double price, String status, String category) {
        this.name = name;
        this.price = price;
        this.internalStatus = status;
        this.category = category;
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

    @Override
    public String toString() {
        return "ProductRequestDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", internalStatus='" + internalStatus + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
