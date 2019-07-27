package com.lastminute.salestaxes.facade.impl;

import com.lastminute.salestaxes.dto.ProductDTO;
import com.lastminute.salestaxes.dto.ReceiptDTO;
import com.lastminute.salestaxes.dto.TaxDTO;
import com.lastminute.salestaxes.facade.ReceiptFacadeService;
import com.lastminute.salestaxes.service.ProductService;
import com.lastminute.salestaxes.util.TaxCalculatorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReceiptFacadeServiceImpl implements ReceiptFacadeService {

    private static BigDecimal ROUNDING_FACTOR;

    @Value("${rounding.factor}")
    public void setRoundingFactor(String roundingFactor) {
        ROUNDING_FACTOR = new BigDecimal(roundingFactor);
    }

    @Autowired
    private ProductService productService;

    @Autowired
    Map<String, TaxCalculatorStrategy> taxCalculator;

    @Override
    public ReceiptDTO generateReceipt(List<Integer> productList) {
        log.info("generateReceipt for product Ids: {}", productList);
        List<ProductDTO> productDTOList = productService.retrieveProducts(productList);
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalTaxAmount = BigDecimal.ZERO;
        for (ProductDTO productDTO : productDTOList) {
            BigDecimal priceAmount = productDTO.getPriceAmount();
            BigDecimal taxAmount = sumTaxes(priceAmount, productDTO.getTaxList());
            productDTO.setTaxAmount(taxAmount);
            BigDecimal taxedPriceAmount = priceAmount.add(taxAmount);
            productDTO.setTaxedPriceAmount(taxedPriceAmount);
            totalTaxAmount = totalTaxAmount.add(taxAmount);
            totalAmount = totalAmount.add(taxedPriceAmount);
        }
        log.info("totalTaxAmount: {} - totalAmount: {}", totalTaxAmount, totalAmount);
        return new ReceiptDTO(productDTOList, totalTaxAmount, totalAmount);
    }

    private BigDecimal sumTaxes(BigDecimal productPriceAmount, List<TaxDTO> taxDTOList) {
        return taxDTOList
                .stream()
                .map(t -> round(taxCalculator.get(t.getModifier().name()).apply(productPriceAmount, t.getTaxAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal round(BigDecimal amount) {
        return amount.divide(ROUNDING_FACTOR, 0, RoundingMode.UP).multiply(ROUNDING_FACTOR);
    }
}