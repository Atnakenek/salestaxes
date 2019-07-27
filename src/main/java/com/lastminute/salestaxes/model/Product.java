package com.lastminute.salestaxes.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany
    private List<ProductPricing> pricing;

    @ManyToMany
    private Set<Attribute> attributes;
}
