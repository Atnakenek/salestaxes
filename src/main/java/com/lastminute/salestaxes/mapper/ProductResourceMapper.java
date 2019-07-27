package com.lastminute.salestaxes.mapper;

import com.lastminute.salestaxes.dto.ProductDTO;
import com.lastminute.salestaxes.resource.ProductResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductResourceMapper {

    @Mapping(source = "description", target = "description")
    @Mapping(source = "taxedPriceAmount", target = "price")
    @Mapping(target = "quantity", defaultValue = "1") //hardcoded - out of scope
    ProductResource fromProductDTO(ProductDTO productDTO);

}