package com.killiann.lynBack.controllers;

import com.killiann.lynBack.exceptions.PizzaNotFoundException;
import com.killiann.lynBack.models.Pizza;
import com.killiann.lynBack.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class PizzaController {
    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping("/pizzas")
    public List<Pizza> all() {
        return pizzaRepository.findAll();
    }

    @GetMapping("/pizzas/{pizza_id}")
    public Pizza one(@PathVariable Long pizza_id) {
        return pizzaRepository.findById(pizza_id).orElseThrow(() -> new PizzaNotFoundException(pizza_id));
    }

    @PostMapping("/pizzas")
    Pizza newPizza(@RequestBody Pizza newPizza) {
        return pizzaRepository.save(newPizza);
    }

    @PutMapping("/pizzas/{id}")
    Pizza replacePizza(@RequestBody Pizza newPizza, @PathVariable Long id) {

        return pizzaRepository.findById(id)
                .map(pizza -> {
                    pizza.setName(newPizza.getName());
                    pizza.setPrice(newPizza.getPrice());
                    return pizzaRepository.save(pizza);
                })
                .orElseThrow(() -> new PizzaNotFoundException(id));
    }

    @DeleteMapping("/pizzas/{id}")
    void deletePizza(@PathVariable Long id) {
        pizzaRepository.deleteById(id);
    }

}
