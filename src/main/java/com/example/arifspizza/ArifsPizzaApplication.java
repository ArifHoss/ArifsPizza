package com.example.arifspizza;

import com.example.arifspizza.entities.Pizza;
import com.example.arifspizza.repositories.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArifsPizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArifsPizzaApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(PizzaRepository pizzaRepository) {
        return args -> {
            if (pizzaRepository.count() == 0) {
                pizzaRepository.save(new Pizza(0L,"Supreme", 179, "Tomatsås, Mozzarella, Pepperonikorv, Beef, Fäska champinjoner, Paprika och Rödlök"));
            }
        };
    }
}
