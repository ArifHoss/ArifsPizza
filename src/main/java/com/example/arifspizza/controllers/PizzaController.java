package com.example.arifspizza.controllers;

import com.example.arifspizza.entities.Pizza;
import com.example.arifspizza.repositories.PizzaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
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

    @GetMapping("/pizzas/{ingredients}")
    public void findAllByThisIngredient(@PathVariable String ingredients, @RequestBody Pizza body){
        pizzaRepository.findAll();
        String getIn = body.getIngredients();
        System.out.println(getIn);
        //return find.contains(getIn);
    }

    @PostMapping("/pizzas")
    public Pizza addNewPizza(@RequestBody Pizza pizzaNew) {
        return pizzaRepository.save(pizzaNew);
    }

    @PutMapping("/pizzas")
    public Pizza updatePizza(@RequestBody Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    @PatchMapping(("/pizzas/{id}"))
    public Pizza updatePizzaId(@PathVariable Long id,@RequestBody Pizza body){

        Optional<Pizza> findPizzaToUpdate = pizzaRepository.findById(id);

        if(findPizzaToUpdate.isPresent()){
            Pizza pizza = findPizzaToUpdate.get();
            pizza.setId(body.getId());
            return pizzaRepository.save(pizza);

        }else {
            throw new RuntimeException("There is no pizza with this id: "+id+"! Try again with right id");
        }
    }


    @PatchMapping(("/pizzas/{id}/{name}"))
    public Pizza updatePizzaName(@PathVariable Long id, @PathVariable String name,@RequestBody Pizza body){
        Optional<Pizza> findPizzaToUpdate = pizzaRepository.findById(id);
        if (findPizzaToUpdate.isPresent()){
            Pizza pizza = findPizzaToUpdate.get();
            pizza.setName(body.getName());
            return pizzaRepository.save(pizza);
        }else {
            throw new RuntimeException("There is no pizza with this id: "+id);
        }
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
