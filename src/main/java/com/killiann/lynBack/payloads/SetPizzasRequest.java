package com.killiann.lynBack.payloads;

import com.killiann.lynBack.models.Pizza;

import java.util.List;

public class SetPizzasRequest {
    private List<Long> pizzaIds;

    public SetPizzasRequest() {}

    public SetPizzasRequest(List<Long> pizzaIds) {
        this.pizzaIds = pizzaIds;
    }

    public List<Long> getPizzaIds() {
        return pizzaIds;
    }

    public void setPizzaIds(List<Long> pizzaIds) {
        this.pizzaIds = pizzaIds;
    }
}
