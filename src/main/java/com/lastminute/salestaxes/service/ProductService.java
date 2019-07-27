package com.lastminute.salestaxes.service;

import com.lastminute.salestaxes.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> retrieveProducts(List<Integer> productIds);
}