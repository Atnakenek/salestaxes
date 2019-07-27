package com.lastminute.salestaxes.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @OneToMany
    private List<Product> products;

    @OneToMany
    private List<CategoryTax> taxEntities;

}
