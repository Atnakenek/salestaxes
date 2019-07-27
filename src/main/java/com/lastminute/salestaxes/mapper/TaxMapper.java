package com.lastminute.salestaxes.mapper;

import com.lastminute.salestaxes.dto.TaxDTO;
import com.lastminute.salestaxes.model.Tax;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaxMapper {

    @Mapping(source = "taxAmount", target = "taxAmount")
    @Mapping(source = "modifier", target = "modifier")
    TaxDTO toTaxDTO(Tax tax);

}