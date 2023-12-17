package com.killiann.lynBack.exceptions;

public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException(Long id) {
        super("Could not find pizza with id: " + id);
    }
}