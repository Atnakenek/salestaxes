package com.lastminute.salestaxes.util.impl;

import com.lastminute.salestaxes.util.TaxCalculatorStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component(value = "PERC")
public class PercentageTaxCalculator implements TaxCalculatorStrategy {

    public static final BigDecimal PERCENTAGE_100 = new BigDecimal(100);

    @Override
    public BigDecimal apply(BigDecimal amount, BigDecimal taxAmount) {

        return amount.multiply(taxAmount).divide(PERCENTAGE_100);
    }
}