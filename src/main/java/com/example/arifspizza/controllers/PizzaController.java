package com.example.arifspizza.controllers;

import com.example.arifspizza.entities.Pizza;
import com.example.arifspizza.repositories.PizzaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/pizzas")
    public List<Pizza> pizzas() {
        return pizzaRepository.findAll();
    }

    @GetMapping("/pizzas/{id}")
    public Pizza get(@PathVariable Long id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isPresent()) {
            return pizza.get();
        } else {
            throw new RuntimeException("There is no pizza found with this id: " + id + "! Try again with right Id.");
        }
    }

    @PostMapping("/pizzas")
    public Pizza addNewPizza(@RequestBody Pizza pizzaNew) {
        return pizzaRepository.save(pizzaNew);
    }

    @DeleteMapping("/pizzas/{id}")
    public void removePizza(@PathVariable Long id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isPresent()) {
            pizzaRepository.deleteById(id);
            System.out.println("Pizza with this id " + id + " has been successfully removed from list");
        } else {
            throw new RuntimeException("There is no pizza with this id: " + id);
        }
    }
}
