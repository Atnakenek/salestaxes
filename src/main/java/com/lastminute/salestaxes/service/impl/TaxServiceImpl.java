package com.lastminute.salestaxes.service.impl;

import com.lastminute.salestaxes.dto.SalesTaxesDTO;
import com.lastminute.salestaxes.model.Product;
import com.lastminute.salestaxes.repository.ProductRepository;
import com.lastminute.salestaxes.service.TaxService;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TaxServiceImpl implements TaxService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public SalesTaxesDTO calculateTaxes(List<BigInteger> products) {

        List<Product> productList = IterableUtils.toList(productRepository.findAllById(products));
        //TODO logica per settare le tasse arrotondate sull'oggetto, il totale, e il totale tasse (da arrotondare solo dopo)
        return new SalesTaxesDTO();
    }
}
