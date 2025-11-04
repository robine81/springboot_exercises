package com.example.springboot_exercises.hello_spring;

import java.util.ArrayList;

public class GreetingService {
    private final ArrayList<Greeting> greetings = new ArrayList<>();

    public void addGreeting(Greeting greeting) {
        greetings.add(greeting);
    }

    public ArrayList<Greeting> getGreetings(){
        return greetings;
    }
}
