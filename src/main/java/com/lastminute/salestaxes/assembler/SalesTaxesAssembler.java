package com.lastminute.salestaxes.assembler;

import com.lastminute.salestaxes.controller.SalesTaxController;
import com.lastminute.salestaxes.dto.SalesTaxesDTO;
import com.lastminute.salestaxes.resource.SalesTaxesResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class SalesTaxesAssembler extends ResourceAssemblerSupport<SalesTaxesDTO, SalesTaxesResource> {

    public SalesTaxesAssembler() {
        super(SalesTaxController.class, SalesTaxesResource.class);
    }

    @Override
    public SalesTaxesResource toResource(SalesTaxesDTO salesTaxes) {

        SalesTaxesResource salesTaxesResource = createResourceWithId("taxes", salesTaxes);
//TODO copy
        return salesTaxesResource;
    }
}