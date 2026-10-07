package org.example.proyecto1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(
            @RequestParam(value = "name", defaultValue = "Mundo") String name,
            @RequestParam(value = "lang", defaultValue = "ES") String lang) {
        
        // Condición adicional: si alguien manda el nombre vacío o con espacios
        if (name == null || name.trim().isEmpty()) {
            name = "Invitado Anónimo";
        }
        
        String greeting;
        switch (lang.toUpperCase()) {
            case "EN":
                greeting = "Hello %s!";
                break;
            case "BR":
                greeting = "Olá %s!";
                break;
            case "FR": // Nuevo idioma: Francés
                greeting = "Bonjour %s!";
                break;
            case "ES":
            default:
                greeting = "Hola %s!";
                break;
        }
        
        return String.format("<h1>" + greeting + "</h1>", name);
    }
}