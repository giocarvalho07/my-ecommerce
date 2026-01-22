package com.mycommerce.service;

import com.mycommerce.enums.StatusOrder;
import com.mycommerce.model.Order;
import com.mycommerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order){
        order.setDateOrder(LocalDateTime.now());
        order.setStatus(StatusOrder.PENDING);
        order.setNumberOrder(UUID.randomUUID().toString().substring(0,8).toUpperCase());

        BigDecimal plusItem = BigDecimal.ZERO;

        for(var item : order.getItemOrders()) {
            item.setOrder(order);

            BigDecimal subtotalItem = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getAmount()));
            item.setSubtotal(subtotalItem);
        }

        order.setTotalValue(plusItem.add(order.getFeeDelivery()));
        return orderRepository.save(order);
    }

    public List<Order> consultByCustomerOrder(Long id){
        return orderRepository.findByCustomerId(id);
    }

    public Order updateStatusOrder(Long id, StatusOrder newStatus){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

}
