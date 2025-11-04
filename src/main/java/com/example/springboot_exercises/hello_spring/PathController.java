package com.example.springboot_exercises.hello_spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PathController {

    CalculatorService service = new CalculatorService();
    GreetingService greetingService = new GreetingService();

    // Uppgift 1 – PathVariable
    // Skapa en endpoint som tar emot ett namn via URL:en.
    // Returnera ett meddelande som hälsar användaren med det namnet.
    // Testa i webbläsaren med olika namn i URL:en.
    @GetMapping("/pathtest/{pathinput}")
    public String getInput(@PathVariable String pathinput){
        return pathinput;
    }

    //Uppgift 2 – RequestParam
    // Skapa en endpoint som tar emot två tal som parametrar.
    // Beräkna produkten av talen och returnera resultatet.
    // Lägg till ett standardvärde för en av parametrarna om den inte skickas in.
    @GetMapping("/addition")
    public int addition(@RequestParam (required = false, defaultValue = "3") int input1, @RequestParam int input2){
        return input1 + input2;
    }

    @GetMapping("/multiply/{input1}/{input2}")
    public int calculate(@PathVariable int input1, @PathVariable int input2){
        return service.multiply(input1, input2);
    }

    //Uppgift 3 – POST med JSON
    // Skapa en endpoint som tar emot ett objekt i JSON-format via en POST-request.
    //Returnera ett meddelande som innehåller informationen från objektet.
    // Testa att skicka olika JSON-data i Postman.
    @PostMapping("/message")
    public Greeting message(@RequestBody Greeting greeting) {
        return greeting;
    }

    //to get only variable message with record
    @PostMapping("/messageString")
    public String messageString(@RequestBody Greeting greeting) {
        return greeting.message();
    }

    // Uppgift 4 – Lista i minnet
    // Skapa en endpoint som tar emot objekt och sparar dem i en lista i minnet
    // Skapa en annan endpoint som returnerar hela listan.
    // Testa genom att lägga till flera objekt och hämta alla.
    @PostMapping("/saveToGreetingList")
    public void saveToList(@RequestBody Greeting greeting){
        greetingService.addGreeting(greeting);
    }

    @GetMapping("/getGreetingList")
    public ArrayList<Greeting> getGreetingList(){
        return greetingService.getGreetings();
    }


    //Uppgift 5 – Hämta objekt via ID
    // Skapa en endpoint som tar emot ett ID som del av URL:en.
    // Låt endpointen söka i listan efter objektet med det ID:t.
    // Returnera objektet om det finns, annars ett felmeddelande (t.ex. ”Objektet finns inte”).
    // Testa genom att lägga till flera objekt och sedan hämta ett specifikt via ID.
    @GetMapping("/findGreetingById/{id}")
    public Greeting findGreetingById(@PathVariable int id){
        for (Greeting g : greetingService.getGreetings()){
            if (g.id() == id){
                return g;
            }
        }
        throw new RuntimeException("Objektet finns inte");
    }

    //Uppgift 5 med felmeddelande i browsern
    @GetMapping("/findGreetingByIdResponseEntity/{id}")
    public ResponseEntity<?> findGreetingByIdResponseEntity(@PathVariable int id){
        for (Greeting g : greetingService.getGreetings()){
            if (g.id() == id){
                return ResponseEntity.ok(g);  // 200 OK - returnerar Greeting som JSON
            }
        }
        return ResponseEntity.status(404).body("Objektet finns inte");  // 404 - visar text
    }
}
