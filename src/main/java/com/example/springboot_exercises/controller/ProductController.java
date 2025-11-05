package com.example.springboot_exercises.controller;

import com.example.springboot_exercises.model.Product;
import com.example.springboot_exercises.model.ProductCreateDto;
import com.example.springboot_exercises.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @GetMapping
    public List<Product> getAll(){ return service.getAll(); }

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

    //Uppgift 2: Filtrera resurser med RequestParam
    // Skapa en GET-endpoint som returnerar en lista av objekt.
    // Använd @RequestParam för att filtrera på ett eller flera fält.
    // Parametrar ska vara valfria.
    //Returnera en lista av DTO-objekt.
    //localhost:8081/products/search?name=iphone
    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) { return service.searchByName(name); }

    //Uppgift 3: Skapa en resurs
    // Skapa en POST-endpoint enligt REST-konventioner.
    // Ta emot ett DTO-objekt som request body.
    // Validera fälten med @Valid.
    // Returnera det skapade objektet med statuskod 201.
    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductCreateDto productDto){
        Product result = new Product(-1, productDto.name(), productDto.price(), productDto.status());
        result = service.addProduct(result);
        //validation not working
        return ResponseEntity.status(201).body(result);
    }

    //Uppgift 4: Uppdatera en resurs
    // Skapa en PUT-endpoint enligt REST-konventioner.
    // Använd @PathVariable för att specificera id.
    // Ta emot ett DTO-objekt med nya värden.
    // Returnera uppdaterad responsmodell.
    // Hantera fall där id inte finns (404).
    //http://localhost:8081/products/3?name=Huawei&price=170.0
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestParam String name, @RequestParam double price, @RequestParam String status){
        //id -1 will be overwritten in service.updateProduct with findById(id), -1 is just a placeholder to create object
        Product result = service.updateProduct(id, new Product (-1, name, price, status));
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Uppgift 5: Ta bort en resurs
    // Skapa en DELETE-endpoint enligt REST-konventioner.
    // Använd @PathVariable för att identifiera objektet.
    // Returnera 204 vid lyckad borttagning.
    // Returnera 404 om objektet inte hittas.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean removed = service.deleteById(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    //Uppgift 6: Sökning med flera RequestParam
    //Skapa en GET-endpoint som tillåter sökning baserat på flera filter via @RequestParam.
    // Parametrar kan kombineras (t.ex. namn, datumintervall, status).
    // Hantera avsaknad av parametrar genom att returnera alla resultat.
    // Returnera en lista av DTO-objekt som matchar sökvillkoren.
    @GetMapping("/search_many")
    public List<Product> searchMany(@RequestParam String name, @RequestParam int price, @RequestParam String status){
        //UPDATE HERE
        return service.searchByName(name);
    }


    //Uppgift 7: Kombinera PathVariable och RequestParam
    // Skapa en GET-endpoint där @PathVariable anger en kategori och @RequestParam filtrerar resultat inom kategorin.
    // Returnera en lista av DTO-objekt.
    // Returnera 400 vid ogiltiga parametrar.

    //Uppgift 8: Anpassad ResponseEntity-hantering
    // Använd ResponseEntity i alla controller-metoder.
    // Sätt rätt HTTP-statuskod för varje typ av operation.
    // Inkludera eventuella headers (t.ex. Location vid skapande).

    // Returnera DTO-objekt i body.
    // Uppgift 9: Responsmodell med metadata
    // Skapa en responsmodell som innehåller data, timestamp och requestId. ● Alla endpoints ska returnera denna struktur.
    // timestamp sätts automatiskt vid varje svar.
    // requestId genereras unikt per anrop.
}
