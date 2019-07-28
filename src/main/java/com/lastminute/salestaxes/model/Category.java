package com.lastminute.salestaxes.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"categoryName"})
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope = Category.class)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

//    @OneToMany(mappedBy = "category")
//    private List<Product> productList;

    @OneToMany(mappedBy = "category")
    private List<CategoryTax> taxes;
}