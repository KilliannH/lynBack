package com.killiann.lynBack.controllers;

import com.killiann.lynBack.exceptions.OrderNotFoundException;
import com.killiann.lynBack.exceptions.UserNotFoundException;
import com.killiann.lynBack.models.Order;
import com.killiann.lynBack.models.Pizza;
import com.killiann.lynBack.models.User;
import com.killiann.lynBack.payloads.SetOwnerRequest;
import com.killiann.lynBack.payloads.SetPizzasRequest;
import com.killiann.lynBack.repositories.OrderRepository;
import com.killiann.lynBack.repositories.PizzaRepository;
import com.killiann.lynBack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping("/orders")
    public List<Order> all() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{order_id}")
    public Order one(@PathVariable Long order_id) {
        return orderRepository.findById(order_id).orElseThrow(() -> new OrderNotFoundException(order_id));
    }

    @PostMapping("/orders")
    Order newOrder(@RequestBody Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @PutMapping("/orders/{id}")
    Order replaceOrder(@RequestBody Order newOrder, @PathVariable Long id) {

        return orderRepository.findById(id)
                .map(order -> {
                    order.setOwner(newOrder.getOwner());
                    order.setSold(newOrder.isSold());
                    order.setPizzas(newOrder.getPizzas());
                    order.setUpdatedAt(newOrder.getUpdatedAt());
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }

    // remove, add relations


    @PostMapping("/orders/{id}/setOwner")
    Order setOwner(@RequestBody SetOwnerRequest setOwnerRequest, @PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        User owner = userRepository.findById(setOwnerRequest.getOwner()).orElseThrow(() -> new UserNotFoundException(setOwnerRequest.getOwner()));

        order.setOwner(owner);
        return orderRepository.save(order);
    }

    @PostMapping("/orders/{id}/setPizzas")
    Order setPizzas(@RequestBody SetPizzasRequest setPizzasRequest, @PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        Boolean checkPizzaIds = pizzaRepository.existsAllByIdIn(setPizzasRequest.getPizzaIds());

        if(!checkPizzaIds) {
            throw new RuntimeException("Error : Found unexpected pizzas in the list");
        }

        List<Pizza> pizzas = pizzaRepository.findAllById(setPizzasRequest.getPizzaIds());
        order.setPizzas(pizzas);
        return orderRepository.save(order);
    }

}
