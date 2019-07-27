package com.lastminute.salestaxes.service;

import com.lastminute.salestaxes.dto.SalesTaxesDTO;

import java.math.BigInteger;
import java.util.List;

public interface TaxService {

    SalesTaxesDTO calculateTaxes(List<BigInteger> productIds);
}
