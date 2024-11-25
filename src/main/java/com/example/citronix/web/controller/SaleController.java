package com.example.citronix.web.controller;

import com.example.citronix.model.Sale;
import com.example.citronix.service.SaleService;
import com.example.citronix.web.VM.mapper.SaleMapper;
import com.example.citronix.web.VM.sale.SaleRequestVm;
import com.example.citronix.web.VM.sale.SaleResponseVm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sale")
public class SaleController {
    private SaleService saleService;
    private final SaleMapper saleMapper;


    public SaleController(SaleService saleService, SaleMapper saleMapper) {
        this.saleService = saleService;
        this.saleMapper = saleMapper;
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<SaleResponseVm> saveTree(@PathVariable Long id, @RequestBody @Valid SaleRequestVm saleRequestVm) {
        Sale saleToSave = saleMapper.toEntity(saleRequestVm);
        Sale savedSale = saleService.createSale(id,saleToSave);
        SaleResponseVm saleResponseVM = saleMapper.toResponseVm(savedSale);
        return ResponseEntity.status(HttpStatus.CREATED).body(saleResponseVM);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok("Sale deleted successfully");
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<SaleResponseVm> getSaleById(@PathVariable Long id) {
        Sale sale = saleService.findById(id);
        SaleResponseVm saleResponseVM = saleMapper.toResponseVm(sale);
        return ResponseEntity.ok(saleResponseVM);
    }
}
