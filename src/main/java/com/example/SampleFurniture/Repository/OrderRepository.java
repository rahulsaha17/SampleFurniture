package com.example.SampleFurniture.Repository;

import com.example.SampleFurniture.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
