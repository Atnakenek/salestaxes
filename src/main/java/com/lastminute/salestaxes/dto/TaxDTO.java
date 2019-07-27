package com.lastminute.salestaxes.dto;

import com.lastminute.salestaxes.util.types.Modifier;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TaxDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal taxAmount;
    private Modifier modifier;
}
