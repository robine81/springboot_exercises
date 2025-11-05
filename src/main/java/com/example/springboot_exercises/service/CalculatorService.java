package com.example.springboot_exercises.service;

import com.example.springboot_exercises.model.Calculator;

@org.springframework.stereotype.Service
public class CalculatorService {

    public Calculator calculator(){ return new Calculator(); }

    public int multiply(int first, int second){
        return first * second;
    }
}