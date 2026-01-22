package com.mycommerce.repository;

import com.mycommerce.enums.StatusOrder;
import com.mycommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByStatus(StatusOrder status);

    @Query("""
            SELECT o FROM Order o
            WHERE o.dateOrder  BETWEEN :start AND :end
    """)
    List<Order> findByDateTime(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

}
