package com.example.springboot_exercises.controller;

import com.example.springboot_exercises.model.Greeting;
import com.example.springboot_exercises.service.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class BasicController {

    @Value("${app.welcome-message}")
    String message;
    Service service;

    public BasicController(Service service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public Greeting sayHello(){
        return service.greeting();
    }

    @GetMapping("/bye")
    public String sayGoodbye(){
        return message;
    }
}
