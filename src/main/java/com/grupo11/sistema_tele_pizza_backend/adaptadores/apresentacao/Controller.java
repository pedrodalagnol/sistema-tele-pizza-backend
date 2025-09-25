package com.grupo11.sistema_tele_pizza_backend.adaptadores.apresentacao;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("")
    @CrossOrigin("*")
    public String welcomeMessage() {
        return "Bem Vindo a Pizzaria ECA";
    }
}
