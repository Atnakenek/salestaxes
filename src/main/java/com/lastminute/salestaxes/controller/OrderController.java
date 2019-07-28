package com.lastminute.salestaxes.controller;

import com.lastminute.salestaxes.assembler.ReceiptAssembler;
import com.lastminute.salestaxes.dto.ReceiptDTO;
import com.lastminute.salestaxes.facade.ReceiptFacadeService;
import com.lastminute.salestaxes.resource.ReceiptResource;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/orders")
@Slf4j
public class OrderController {

    @Autowired
    private ReceiptFacadeService receiptFacadeService;
    @Autowired
    private ReceiptAssembler receiptAssembler;

    @PostMapping(path = "/receipt",
            consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    @ApiOperation(value = "API that returns total taxes and total priceAmount of a list of product Ids")
    public ResponseEntity<ReceiptResource> getReceipt(
            @RequestBody List<Integer> productList) {

        ReceiptDTO receiptDTO =  receiptFacadeService.generateReceipt(productList);
        return ResponseEntity.ok(receiptAssembler.toResource(receiptDTO));
    }

}
