package com.lastminute.salestaxes.controller;

import com.lastminute.salestaxes.assembler.SalesTaxesAssembler;
import com.lastminute.salestaxes.dto.ProductDTO;
import com.lastminute.salestaxes.dto.SalesTaxesDTO;
import com.lastminute.salestaxes.resource.SalesTaxesResource;
import com.lastminute.salestaxes.service.TaxService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/sales", produces = {APPLICATION_JSON_VALUE})
@Slf4j
public class SalesTaxController {

    @Autowired
    private TaxService taxService;

    @Autowired
    private SalesTaxesAssembler salesTaxesAssembler;


    @PostMapping(path = "/taxes",
            consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    @ApiOperation(value = "API that returns total taxes and total price of a list of product Ids")
    public ResponseEntity<SalesTaxesResource> getTaxes (
            @RequestBody List<BigInteger> productIds) {

        log.debug("prova {}", "prova");

        SalesTaxesDTO salesTaxesDTO = taxService.calculateTaxes(productIds);

    /*    final FidoGaranziaBin modelIn = fidoGaranziaFactory.create(idPratica, numVersione);

        final GetFidoGaranziaCommand command = beanFactory.getBean(GetFidoGaranziaCommand.class, modelIn);
        final List<FidoGaranzia> modelsOut = command.execute();

        final List<FidoGaranziaResource> response = fidoGaranziaResourceAssembler.toResources(modelsOut);*/
        return ResponseEntity.ok(salesTaxesAssembler.toResource(salesTaxesDTO));
    }

}
