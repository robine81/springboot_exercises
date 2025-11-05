package com.example.springboot_exercises.model;

import java.time.LocalDate;

public class Product {
    private int id;
    private String name;
    private double price;
    private String status;
    //private LocalDate createdDate;
    //private String category;

    public Product(int id, String name, double price, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        //this.createdDate = createdDate;
        //this.category = category;
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

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    //public LocalDate getCreatedDate() {
    //    return createdDate;
    //}

    //public void setCreatedDate(LocalDate createdDate) {
    //    this.createdDate = createdDate;
    //}

    //public String getCategory() {
    //    return category;
    //}

    //public void setCategory(String category) {
    //    this.category = category;
    //}

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                //", createdDate=" + createdDate +
                //", category='" + category + '\'' +
                '}';
    }
}
