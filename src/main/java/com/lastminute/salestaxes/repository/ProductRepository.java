package com.lastminute.salestaxes.repository;

import com.lastminute.salestaxes.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}