package com.lastminute.salestaxes.mapper;

import com.lastminute.salestaxes.dto.ReceiptDTO;
import com.lastminute.salestaxes.resource.ReceiptResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductResourceMapper.class})
public interface ReceiptResourceMapper {

    @Mapping(source = "productList", target = "products")
    @Mapping(source = "totalTaxAmount", target = "salesTaxes")
    @Mapping(source = "totalAmount", target = "total")
    ReceiptResource fromReceiptDTO(ReceiptDTO receiptDTO);
}