package com.lastminute.salestaxes.repository;

import com.lastminute.salestaxes.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public interface ProductRepository extends CrudRepository<Product, BigInteger> {

}