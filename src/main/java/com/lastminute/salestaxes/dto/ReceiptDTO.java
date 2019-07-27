package com.lastminute.salestaxes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ReceiptDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ProductDTO> productList;
    private BigDecimal totalTaxAmount;
    private BigDecimal totalAmount;

}
