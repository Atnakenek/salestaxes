package com.lastminute.salestaxes.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ATTRIBUTE")
@Data
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "SIZE")
    private String size;

    @Column(name = "IMPORTED")
    private Boolean imported;

    @OneToMany
    private List<AttributeTax> taxEntities;

    @ManyToMany
    private Set<Product> products;

}
