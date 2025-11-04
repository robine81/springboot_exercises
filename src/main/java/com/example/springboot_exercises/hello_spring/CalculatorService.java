package com.example.springboot_exercises.hello_spring;

@org.springframework.stereotype.Service
public class CalculatorService {

    public Calculator calculator(){ return new Calculator(); }

    public int multiply(int first, int second){
        return first * second;
    }
}