package com.lastminute.salestaxes.util.impl;

import com.lastminute.salestaxes.util.TaxCalculatorStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component(value = "FLAT")
public class FlatTaxCalculator implements TaxCalculatorStrategy {

    @Override
    public BigDecimal apply(BigDecimal amount, BigDecimal taxAmount) {
        return amount.add(taxAmount);
    }
}