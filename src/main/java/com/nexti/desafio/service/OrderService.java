package com.nexti.desafio.service;

import com.nexti.desafio.model.Order;
import com.nexti.desafio.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }

    public Order update(Long id, Order order) {
        Optional<Order> savedOrder = orderRepository.findById(id);
        Order updatedOrder;

        if(savedOrder.isPresent()){
            updatedOrder = savedOrder.get();
            BeanUtils.copyProperties(order, updatedOrder);
            orderRepository.save(updatedOrder);
        }else{
            return null;
        }
        return updatedOrder;
    }

    public Boolean delete(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
