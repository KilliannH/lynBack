package com.killiann.lynBack.controllers;

import com.killiann.lynBack.exceptions.OrderNotFoundException;
import com.killiann.lynBack.exceptions.PizzaNotFoundException;
import com.killiann.lynBack.models.Order;
import com.killiann.lynBack.models.Pizza;
import com.killiann.lynBack.repositories.OrderRepository;
import com.killiann.lynBack.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

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


}
