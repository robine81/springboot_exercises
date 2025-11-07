package com.example.springboot_exercises.controller;

import com.example.springboot_exercises.model.dto.ProductRequestDTO;
import com.example.springboot_exercises.model.dto.ProductResponseDTO;
import com.example.springboot_exercises.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ProductService service;

    public AdminController(ProductService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() { return ResponseEntity.ok(service.getAll());}

    @GetMapping("/search_many")
    public ResponseEntity<List<ProductResponseDTO>> searchMany(@RequestParam(required=false) String name, @RequestParam (required=false) Integer price, @RequestParam (required=false) String status){
        List<ProductResponseDTO> resultList = new ArrayList<>();
        if (name != null && price != null && status != null) {
            //MEST specifik - alla tre parametrar
            resultList.addAll(service.searchByNameAndPriceAndStatus(name, price, status));

        } else if (name != null && price != null) {
            // Två parametrar: namn + pris
            resultList.addAll(service.searchByNameAndPrice(name, price));

        } else if (name != null && status != null) {
            // Två parametrar: namn + status
            resultList.addAll(service.searchByNameAndStatus(name, status));

        } else if (price != null && status != null) {
            // Två parametrar: pris + status
            resultList.addAll(service.searchByPriceAndStatus(price, status));

        } else if (name != null) {
            // En parameter: namn
            resultList.addAll(service.searchByName(name));

        } else if (price != null) {
            // En parameter: pris
            resultList.addAll(service.searchByPrice(price));

        } else if (status != null) {
            // En parameter: status
            resultList.addAll(service.searchByStatus(status));

        } else {
            //MINST specifik - inga parametrar (visa alla produkter)
            resultList.addAll(service.getAll());
        }
        return ResponseEntity.ok(resultList);
    }
}
