package com.example.exercisejsonprocessing.service;

import com.example.exercisejsonprocessing.entities.products.Product;
import com.example.exercisejsonprocessing.entities.products.ProductDTO;
import com.example.exercisejsonprocessing.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override

    public List<ProductDTO> getAllProductsInRangeWhichHaveNoBuyer(float from, float to) {
        BigDecimal start = BigDecimal.valueOf(from);
        BigDecimal end = BigDecimal.valueOf(to);
        return this.productRepository.getAllProductWhichHaveNoBuyerAndPriceBetween(start, end);
    }
}
