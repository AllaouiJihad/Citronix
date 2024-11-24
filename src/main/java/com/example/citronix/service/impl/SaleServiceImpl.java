package com.example.citronix.service.impl;

import com.example.citronix.exception.HarvestAlreadySoldException;
import com.example.citronix.exception.InvalidCredentialsException;
import com.example.citronix.exception.SaleNotFoundException;
import com.example.citronix.model.Harvest;
import com.example.citronix.model.Sale;
import com.example.citronix.repository.SaleRepository;
import com.example.citronix.service.HarvestService;
import com.example.citronix.service.SaleService;
import org.springframework.stereotype.Component;

@Component(value = "saleServiceImlp")
public class SaleServiceImpl implements SaleService {
    private  SaleRepository saleRepository;
    private HarvestService harvestService;

    public SaleServiceImpl(SaleRepository saleRepository, HarvestService harvestService) {
        this.saleRepository = saleRepository;
        this.harvestService = harvestService;
    }

    @Override
    public Sale createSale(Long harvestId, Sale sale) {
        Harvest harvest = harvestService.findById(harvestId);
        boolean alreadySold = saleRepository.existsByHarvest(harvest);

        if (alreadySold){
            throw new HarvestAlreadySoldException("This harvest already has an associated sale.");
        }

        if(sale.getSaleDate().isBefore(harvest.getHarvestDate())){
            throw new InvalidCredentialsException("Sale date cannot be earlier than the harvest date.");
        }
        sale.setHarvest(harvest);
        return saleRepository.save(sale);
    }

    @Override
    public void deleteSale(Long saleId) {
        Sale sale = findById(saleId);
        saleRepository.delete(sale);
    }

    @Override
    public Sale findById(Long saleId) {
        if (saleId == null){
            throw new InvalidCredentialsException("sale Id is required");
        }
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found."));
    }
}
