package com.lastminute.salestaxes.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ATTRIBUTE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"color", "size", "imported"})
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope = Attribute.class)
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "SIZE")
    private String size;

    @Column(name = "IMPORTED")
    private Boolean imported;

    @OneToMany(mappedBy = "attribute")
    private List<AttributeTax> taxes;

    @ManyToMany(mappedBy = "attributes")
    private Set<Product> products;
}