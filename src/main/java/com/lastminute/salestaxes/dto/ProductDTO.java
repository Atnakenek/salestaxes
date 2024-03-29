package com.lastminute.salestaxes.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String description;
    private BigDecimal priceAmount;
    private List<TaxDTO> taxList;
    private BigDecimal taxedPriceAmount;
    private BigDecimal taxAmount;
}
