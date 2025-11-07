package com.example.springboot_exercises.controller;

import com.example.springboot_exercises.model.Product;
import com.example.springboot_exercises.model.dto.ProductRequestDTO;
import com.example.springboot_exercises.model.dto.ProductResponseDTO;
import com.example.springboot_exercises.model.dto.ProductResponseDTOAdmin;
import com.example.springboot_exercises.model.dto.UserValidationDTO;
import com.example.springboot_exercises.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll(){ return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/admin")
    public ResponseEntity<List<ProductResponseDTOAdmin>> getAllAdmin(){ return ResponseEntity.ok(service.getAllAdmin()); }

    //Uppgift 1 REST-api: Hämta en resurs med PathVariable
    // Skapa en GET-endpoint enligt REST-konventioner.
    // Använd @PathVariable för att hämta ett objekt baserat på dess id.
    //Returnera en responsmodell (DTO).
    //Returnera 404 om objektet inte hittas.
    //localhost:8081/products/3
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id){
        return  service.getById(id)
                .map(p -> ResponseEntity.status(200).body(p))
                .orElse(ResponseEntity.status(404).build());
    }

    @GetMapping("/search")
    public List<ProductResponseDTO> search(@RequestParam String name) { return service.searchByName(name); }

    //localhost:8081/products/
    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productReqDTO){
        ProductResponseDTO producResDTO = service.addProduct(productReqDTO);
        return ResponseEntity.status(201).body(producResDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable int id, @RequestBody ProductRequestDTO productDTO){
        //id -1 will be overwritten in service.updateProduct with findById(id), -1 is just a placeholder to create object
        ProductResponseDTO result = service.updateProduct(id, productDTO);
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean removed = service.deleteById(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search_many")
    public ResponseEntity<List<ProductResponseDTO>> searchMany(@RequestParam (required=false) String name, @RequestParam (required=false) Integer price){
        List<ProductResponseDTO> resultList = new ArrayList<>();
        if (name != null && price != null) {
            // Två parametrar: namn + pris
            resultList.addAll(service.searchByNameAndPrice(name, price));

        } else if (name != null) {
            // En parameter: namn
            resultList.addAll(service.searchByName(name));

        } else if (price != null) {
            // En parameter: pris
            resultList.addAll(service.searchByPrice(price));

        }else {
            //MINST specifik - inga parametrar (visa alla produkter)
            resultList.addAll(service.getAll());
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity <List<ProductResponseDTO>> getProductsByCategory(
            @PathVariable String category,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) String status
            ){
        List<ProductResponseDTO> categoryList = service.searchByCategory(category);

        if (categoryList.isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        if(maxPrice != null && status != null){
            //find products in category matching price and status
            categoryList = categoryList
                    .stream()
                    .filter(p -> p.getPrice() <= maxPrice)
                    .toList();
        } else if(maxPrice != null)
        {
            // find products in category matching price
            categoryList = categoryList
                    .stream()
                    .filter(p -> p.getPrice() <= maxPrice)
                .toList();
        } else if(status != null) {
            //find products in category matching status
            categoryList = categoryList
                    .stream()
                    .toList();
        }

        return  ResponseEntity.ok(categoryList);
    }

    //Uppgift 1: DTO från Query-parametrar
    // Skapa en GET-endpoint som tar emot flera @RequestParam (t.ex. Namn, kategori, minpris, maxpris).
    // Returnera DTO:n som JSON-respons.
    // Hantera saknade parametrar med required = false där det är lämpligt.
    //Uppgift 2: DTO från PathVariable och RequestParam
    // Skapa en GET-endpoint där @PathVariable används för ett id och @RequestParam används för filtrerings- eller sorteringsinformation.
    // Bygg en DTO som kombinerar båda typerna av data.
    // Returnera DTO:n i svaret som JSON.
    // Använd en rimlig defaultValue för @RequestParam om inget värde skickas.

    // Uppgift 3: Konvertering mellan entitet och DTO
    // Skapa en enkel User-entitet med fält som id, email, och role.
    // Skapa motsvarande UserDto för dataöverföring till klienten.
    // Implementera en mapper-klass som konverterar mellan entitet och DTO.
    // Använd en serviceklass som returnerar UserDto via controllern.

    @GetMapping("/create-dto")
    public ResponseEntity<ProductResponseDTO> createDto(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam (required = false) String category)
    {
        ProductRequestDTO productReqDTO = new ProductRequestDTO();
        productReqDTO.setName(name);
        productReqDTO.setPrice(price);
        if(category != null)
        {
            productReqDTO.setCategory(category);
        }

        ProductResponseDTO result = service.addProduct(productReqDTO);
        return ResponseEntity.ok(result);
    }

    //Uppgift 4: Enkel manuell validering av DTO-data
    // Skapa en GET-endpoint som tar emot parametrar för email och age.
    // Kontrollera manuellt i controllern att email innehåller @ och att age är mellan 18–99.
    // Returnera ett textmeddelande som indikerar om datan är giltig eller inte.
    // Skapa en enkel DTO för att hålla värdena


    //TESTA HÄR!!!
    @GetMapping("/validate-user")
    public ResponseEntity<String> validateUser(
            @Valid @RequestParam @RequestBody UserValidationDTO dto){
       return ResponseEntity.ok("Data is valid! Email: " + dto.getEmail() + " and age: " + dto.getAge());

    }


    //Uppgift 5: DTO i ResponseEntity Krav:
    // Skapa en GET-endpoint som returnerar en ResponseEntity<SimpleResponseDto>.
    // DTO:n ska innehålla fält som message, timestamp och success.
        // Returnera statuskod 200 OK vid lyckad operation, och 400 BAD_REQUEST vid misslyckad.
        // Sätt timestamp till aktuell tid och anpassa meddelandet beroende på resultatet.

}