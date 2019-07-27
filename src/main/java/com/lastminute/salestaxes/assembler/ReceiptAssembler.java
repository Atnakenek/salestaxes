package com.lastminute.salestaxes.assembler;

import com.lastminute.salestaxes.controller.OrderController;
import com.lastminute.salestaxes.dto.ReceiptDTO;
import com.lastminute.salestaxes.mapper.ReceiptResourceMapper;
import com.lastminute.salestaxes.resource.ReceiptResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ReceiptAssembler extends ResourceAssemblerSupport<ReceiptDTO, ReceiptResource> {

    @Autowired
    ReceiptResourceMapper receiptResourceMapper;

    public ReceiptAssembler() {
        super(OrderController.class, ReceiptResource.class);
    }

    @Override
    public ReceiptResource toResource(ReceiptDTO receiptDTO) {
        ReceiptResource receiptResource = createResourceWithId("receipt", receiptDTO);
        return receiptResourceMapper.fromReceiptDTO(receiptDTO);
    }
}