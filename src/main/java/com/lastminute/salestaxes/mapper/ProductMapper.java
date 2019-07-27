package com.lastminute.salestaxes.mapper;

import com.lastminute.salestaxes.dto.ProductDTO;
import com.lastminute.salestaxes.model.Product;
import com.lastminute.salestaxes.model.Tax;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring", uses = {TaxMapper.class})
public interface ProductMapper {

    @Mapping(source = "product.productName", target = "description")
    @Mapping(source = "price", target = "priceAmount")
    @Mapping(source = "taxList", target = "taxList")
    ProductDTO toProductDTO(Product product, BigDecimal price, List<Tax> taxList);
}