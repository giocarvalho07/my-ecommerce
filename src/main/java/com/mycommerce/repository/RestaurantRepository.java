package com.mycommerce.repository;

import com.mycommerce.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByActiveTrue();
    List<Restaurant> findByCategory(String category);
    List<Restaurant> findByActiveTrueOrderByEvaluationDesc();
}
