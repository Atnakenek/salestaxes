package com.lastminute.salestaxes.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int quantity;
    private String description;
    private BigDecimal price;
    private BigDecimal taxAmount;
}
