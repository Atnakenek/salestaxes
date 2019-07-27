package com.lastminute.salestaxes.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SalesTaxesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ProductDTO> products;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;

}
