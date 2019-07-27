package com.lastminute.salestaxes.resource;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String quantity;
    private String description;
    private String price;


}
