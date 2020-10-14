package com.nexti.desafio.service;

import com.nexti.desafio.model.Client;
import com.nexti.desafio.model.Order;
import com.nexti.desafio.model.Product;
import com.nexti.desafio.repository.ClientRepository;
import com.nexti.desafio.repository.OrderRepository;
import com.nexti.desafio.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order create(Order order) {
        order.setPurchaseDate(new Date());
        ArrayList<Long> idProducts = new ArrayList<>();
        order.getProducts().forEach(product -> {
            idProducts.add(product.getId());
        });
        List<Product> products = productRepository.findAllById(idProducts);
        order.setProducts(products);
        final BigDecimal[] totalAmount = {BigDecimal.ZERO};
        products.forEach(product -> {
            BigDecimal price = product.getValue().multiply(new BigDecimal(product.getQuantity()));
            totalAmount[0] = totalAmount[0].add(price);
        });
        order.setTotalAmount(totalAmount[0]);

        Optional<Client> client = clientRepository.findById(order.getClient().getId());
        order.setClient(client.get());

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

            ArrayList<Long> idProducts = new ArrayList<>();
            order.getProducts().forEach(product -> {
                idProducts.add(product.getId());
            });
            List<Product> products = productRepository.findAllById(idProducts);
            updatedOrder.setProducts(products);
            final BigDecimal[] totalAmount = {BigDecimal.ZERO};
            products.forEach(product -> {
                BigDecimal price = product.getValue().multiply(new BigDecimal(product.getQuantity()));
                totalAmount[0] = totalAmount[0].add(price);
            });
            updatedOrder.setTotalAmount(totalAmount[0]);

            Optional<Client> client = clientRepository.findById(order.getClient().getId());
            updatedOrder.setClient(client.get());

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
