package com.lastminute.salestaxes.resource;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ReceiptResource extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ProductResource> products;
    private BigDecimal salesTaxes;
    private BigDecimal total;
}
