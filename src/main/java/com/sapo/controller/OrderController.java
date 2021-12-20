package com.sapo.controller;

import com.sapo.constant.ApplicationConstant;
import com.sapo.dto.Account;
import com.sapo.dto.Order;
import com.sapo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        kafkaTemplate.send(ApplicationConstant.TOPIC_NAME, order);
        return orderRepository.save(order);
    }

    @PostMapping("/fake_orders")
    public List<Order> fakeOrders() {
        Random rand = new Random();
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Integer accountId = rand.nextInt((10 - 1) + 1) + 1;
            BigDecimal totalPrice = new BigDecimal(rand.nextInt((999999999 - 1) + 1) + 1);
            Order order = new Order(accountId, totalPrice);
            kafkaTemplate.send(ApplicationConstant.TOPIC_NAME, order.accountId.toString() , order);
            orders.add(order);
        }
        return orderRepository.saveAll(orders);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
