package com.nexti.desafio.service;

import com.nexti.desafio.model.Product;
import com.nexti.desafio.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public Product update(Long id, Product product) {
        Optional<Product> savedProduct = productRepository.findById(id);
        Product updatedProduct;
        if(savedProduct.isPresent()){
            updatedProduct = savedProduct.get();
            BeanUtils.copyProperties(product, updatedProduct, "id");
            productRepository.save(updatedProduct);
        }else{
            return null;
        }
        return updatedProduct;
    }

    public Boolean delete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

}
