package com.killiann.lynBack.repositories;

import com.killiann.lynBack.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }