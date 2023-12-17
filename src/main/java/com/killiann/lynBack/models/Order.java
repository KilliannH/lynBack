package com.killiann.lynBack.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.killiann.lynBack.models.enums.OrderState;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnoreProperties({"orders"})
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_pizzas",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id",
                    referencedColumnName = "id"))
    private List<Pizza> pizzas;

    @JsonIgnoreProperties({"orders"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User owner;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String state;

    private boolean isSold;

    public Order() {}

    public Order(User owner, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isSold = false;
        this.state = OrderState.CREATED.name();
        this.pizzas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isSold() {
        return isSold;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
