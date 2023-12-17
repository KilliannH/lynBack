package com.killiann.lynBack.repositories;

import com.killiann.lynBack.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Boolean existsAllByIdIn(List<Long> ids);
}