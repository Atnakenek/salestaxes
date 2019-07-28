package com.lastminute.salestaxes.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lastminute.salestaxes.dto.ProductDTO;
import com.lastminute.salestaxes.dto.ReceiptDTO;
import com.lastminute.salestaxes.facade.impl.ReceiptFacadeServiceImpl;
import com.lastminute.salestaxes.service.ProductService;
import com.lastminute.salestaxes.util.TaxCalculatorStrategy;
import com.lastminute.salestaxes.util.impl.PercentageTaxCalculator;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReceiptFacadeServiceImplTest {

    @Mock
    private ProductService productService;
    @Mock
    PercentageTaxCalculator percentageTaxCalculator;
    @Mock
    Map<String, TaxCalculatorStrategy> stringTaxCalculatorStrategyMap;

    @InjectMocks
    ReceiptFacadeServiceImpl receiptFacadeServiceImpl;

    @Before
    public void setUp() {
        receiptFacadeServiceImpl.setRoundingFactor("0.05");
        when(stringTaxCalculatorStrategyMap.get(anyString())).thenReturn(percentageTaxCalculator);
        when(percentageTaxCalculator.apply(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(new BigDecimal("1"));
    }

    @Test
    public void generateEmptyReceipt() {
        when(productService.retrieveProducts(anyList())).thenReturn(Lists.emptyList());
        ReceiptDTO receiptDTO = receiptFacadeServiceImpl.generateReceipt(Lists.emptyList());
        assertThat(receiptDTO).isNotNull();
        assertThat(receiptDTO.getProductList()).isEmpty();
        assertThat(receiptDTO.getTotalAmount()).isEqualTo(BigDecimal.ZERO);
        assertThat(receiptDTO.getTotalTaxAmount()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void generate2TaxesOf1() throws IOException {
        InputStream inputJson = new ClassPathResource("mock/ProductDTO.json").getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDTO productDTO = objectMapper.readValue(inputJson, ProductDTO.class);
        when(productService.retrieveProducts(anyList())).thenReturn(Lists.list(productDTO));
        ReceiptDTO receiptDTO = receiptFacadeServiceImpl.generateReceipt(new LinkedList<>());
        assertThat(receiptDTO).isNotNull();
        assertThat(receiptDTO.getProductList()).isNotEmpty();
        assertThat(receiptDTO.getProductList()).containsOnlyOnce(productDTO);
        assertThat(receiptDTO.getTotalAmount()).isEqualTo(new BigDecimal("29.99"));
        assertThat(receiptDTO.getTotalTaxAmount()).isEqualTo(new BigDecimal("2.00"));
    }

}