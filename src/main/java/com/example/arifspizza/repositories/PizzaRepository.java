package com.example.arifspizza.repositories;

import com.example.arifspizza.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza,Long> {

    List<Pizza> findPizzasByIngredientsContains(String ingredient);




}
