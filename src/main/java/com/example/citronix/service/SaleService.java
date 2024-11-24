package com.example.citronix.service;

import com.example.citronix.model.Sale;
import org.springframework.stereotype.Service;

@Service
public interface SaleService {
    Sale createSale(Long harvestId, Sale sale);
    void deleteSale(Long saleId);
    Sale findById(Long saleId);
}
