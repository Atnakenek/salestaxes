package com.lastminute.salestaxes.util;

import java.math.BigDecimal;

public interface TaxCalculatorStrategy {

    BigDecimal apply(BigDecimal amount, BigDecimal taxAmount);
}