package com.example.springboot_exercises.service;


import com.example.springboot_exercises.model.Greeting;

@org.springframework.stereotype.Service
public class Service {
    public Greeting greeting(){
        return new Greeting("Testing");
    }
}


// controller <-> service <-> repository <-> database

//configuration, entities, advice