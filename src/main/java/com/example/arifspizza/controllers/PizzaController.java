package com.example.arifspizza.controllers;

import com.example.arifspizza.entities.Pizza;
import com.example.arifspizza.repositories.PizzaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository){
        this.pizzaRepository=pizzaRepository;
    }

    @GetMapping("/pizzas")
    public List<Pizza> pizzas(){
        return pizzaRepository.findAll();
    }

    @GetMapping("/pizzas/{id}")
    public Pizza get(@PathVariable Long id){
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if(pizza.isPresent()){
            return pizza.get();
        }else{
            throw new RuntimeException("There is no pizza found with this id: "+id+"! Try again with right Id.");
        }
    }
}
