package com.killiann.lynBack.repositories;

import com.killiann.lynBack.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> { }