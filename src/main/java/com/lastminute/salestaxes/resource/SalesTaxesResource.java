package com.lastminute.salestaxes.resource;

import com.lastminute.salestaxes.dto.ProductDTO;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SalesTaxesResource extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ProductDTO> products;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;

}
