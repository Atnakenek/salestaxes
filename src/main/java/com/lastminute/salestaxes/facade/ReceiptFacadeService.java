package com.lastminute.salestaxes.facade;

import com.lastminute.salestaxes.dto.ReceiptDTO;

import java.util.List;

public interface ReceiptFacadeService {

    ReceiptDTO generateReceipt(List<Integer> productList);
}