package com.lastminute.salestaxes.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lastminute.salestaxes.dto.ProductDTO;
import com.lastminute.salestaxes.mapper.ProductMapper;
import com.lastminute.salestaxes.model.Product;
import com.lastminute.salestaxes.repository.ProductRepository;
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
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    ProductMapper productMapper;
    @InjectMocks
    ProductServiceImpl productServiceImpl;

    List<Product> mockedProducts;

    @Before
    public void setUp() throws Exception {
        InputStream inputJson = new ClassPathResource("mock/Product.json").getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        mockedProducts = Arrays.asList(objectMapper.readValue(inputJson,  Product[].class));
        ProductDTO productDTO = mockProductDTO();
        when(productMapper.toProductDTO(any(Product.class), any(BigDecimal.class), anyList())).thenReturn(productDTO);

    }

    @Test
    public void retrieveProducts() throws IOException {
        when(productRepository.findAllById(anyList())).thenReturn(mockedProducts);
        List<ProductDTO> productDTOList = productServiceImpl.retrieveProducts(Lists.list(1, 2, 3));
        assertThat(productDTOList).isNotNull();
        assertThat(productDTOList).hasSize(2);
        assertThat(productDTOList.get(0).getDescription()).isEqualTo("mocked");
        assertThat(productDTOList.get(1).getPriceAmount()).isEqualTo(BigDecimal.ONE);
    }

    private ProductDTO mockProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTaxAmount(BigDecimal.ONE);
        productDTO.setPriceAmount(BigDecimal.ONE);
        productDTO.setDescription("mocked");
        return productDTO;
    }
}