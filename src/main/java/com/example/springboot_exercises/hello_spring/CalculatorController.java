package com.example.springboot_exercises.hello_spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorController {
    CalculatorService calcService = new CalculatorService();

    @GetMapping("/calculate/addition/{input1}/{input2}")
    public int addition(@PathVariable int input1, @PathVariable int input2){
        return input1 + input2;
    }

    @GetMapping("/calculate/subtraction/{input1}/{input2}")
    public int subtraction(@PathVariable int input1, @PathVariable int input2){
        return input1 - input2;
    }

    @GetMapping("/calculate/division/{input1}/{input2}")
    public ResponseEntity<?> division(@PathVariable int input1, @PathVariable int input2){
        if(input2 == 0){
            return ResponseEntity.badRequest().body("Error: cannot be divided by 0");
        }
        return ResponseEntity.ok(input1 / input2);
    }

    @GetMapping("/calculate/multiplication/{input1}/{input2}")
    public int multiplication(@PathVariable int input1, @PathVariable int input2){
        return input1 * input2;
    }

    @GetMapping("/calculate/all")
    public Map<String, Integer> all(@RequestParam int input1, @RequestParam int input2){
        Map<String, Integer> results = new HashMap<>();
        results.put("add", (input1+input2));
        results.put("sub", (input1-input2));
        results.put("mul", (input1*input2));
        if(input2 != 0) {
            results.put("div", (input1 / input2));
        } else {
            throw new RuntimeException("Cannot divide by 0");
        }

        return results;
    }

    @GetMapping("/calculate/factor/{input1}/{input2}")
    public int factor(@PathVariable int input1, @PathVariable int input2,  @Value("${calc.factor}") int factor){
        return (input1 * input2) * factor;
    }


}
