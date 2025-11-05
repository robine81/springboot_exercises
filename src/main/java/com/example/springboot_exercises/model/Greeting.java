package com.example.springboot_exercises.model;

public class Greeting {
    private String message;
    private int id;

    public Greeting(String name) {
        this.message = name;
        this.id = -1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
