package com.lastminute.salestaxes.service.impl;

import com.lastminute.salestaxes.dto.ProductDTO;
import com.lastminute.salestaxes.mapper.ProductMapper;
import com.lastminute.salestaxes.model.*;
import com.lastminute.salestaxes.repository.ProductRepository;
import com.lastminute.salestaxes.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductDTO> retrieveProducts(List<Integer> products) {
        List<Product> productList = IterableUtils.toList(productRepository.findAllById(products));
        List<ProductDTO> productsDtoList = new LinkedList<>();
        for (Product product : productList) {
            BigDecimal price = retrieveCurrentPrice(product.getPricing());
            List<Tax> taxes = retrieveTaxes(price, product.getCategory(), product.getAttributes());
            productsDtoList.add(productMapper.toProductDTO(product, price, taxes));
        }
        return productsDtoList;
    }

    private BigDecimal retrieveCurrentPrice(List<ProductPricing> pricing) {
        return pricing
                .stream()
                .filter(p -> p.getActive() && LocalDateTime.now().isAfter(p.getValidFrom()) && LocalDateTime.now().isBefore(p.getValidTo()))
                .map(ProductPricing::getPrice)
                .findFirst()
                .orElse(null);
    }

    private List<Tax> retrieveTaxes(BigDecimal price, Category category, Set<Attribute> attributes) {
        List<Tax> taxes = category.getTaxes()
                .stream()
                .map(Tax.class::cast)
                .filter(filterActiveTax())
                .collect(Collectors.toList());
        return attributes
                .stream()
                .flatMap(attr -> attr.getTaxes().stream())
                .map(Tax.class::cast)
                .filter(filterActiveTax())
                .collect(Collectors.toCollection(() -> taxes));
    }

    private Predicate<Tax> filterActiveTax() {
        return t -> t.getActive() && LocalDateTime.now().isAfter(t.getValidFrom()) && LocalDateTime.now().isBefore(t.getValidTo());
    }
}