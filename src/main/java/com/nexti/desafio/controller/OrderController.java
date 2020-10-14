package com.nexti.desafio.controller;

import com.nexti.desafio.model.Order;
import com.nexti.desafio.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create (@Valid @RequestBody Order order, HttpServletResponse response) {
        Order newOrder = orderService.create(order);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newOrder.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(newOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orders = orderService.getAll();

        return !orders.isEmpty() ? ResponseEntity.ok(orders) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        Optional<Order> order = orderService.getById(id);

        return order.isPresent() ? ResponseEntity.ok(order.get()) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update (@PathVariable Long id, @Valid @RequestBody Order order) {
        Order updatedOrder = orderService.update(id, order);
        return updatedOrder != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Boolean deletedOrder = orderService.delete(id);
        return deletedOrder ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
