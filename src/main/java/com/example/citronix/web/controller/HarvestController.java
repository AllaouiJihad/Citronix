package com.example.citronix.web.controller;

import com.example.citronix.model.Harvest;
import com.example.citronix.service.HarvestService;
import com.example.citronix.web.VM.harvest.HarvestRequestVm;
import com.example.citronix.web.VM.harvest.HarvestResponseVm;
import com.example.citronix.web.VM.mapper.HarvestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/harvest")
public class HarvestController {

    HarvestService harvestService;
    HarvestMapper harvestMapper;

    public HarvestController(HarvestService harvestService, HarvestMapper harvestMapper) {
        this.harvestService = harvestService;
        this.harvestMapper = harvestMapper;
    }

    @PostMapping("/{fieldId}")
    public ResponseEntity<HarvestResponseVm> createHarvest(@PathVariable Long fieldId, @Valid @RequestBody HarvestRequestVm harvestRequestVm) {
        Harvest harvest = harvestMapper.toEntity(harvestRequestVm);
        Harvest createdHarvest = harvestService.createHarvest(fieldId,harvest);

        return ResponseEntity.status(HttpStatus.CREATED).body(harvestMapper.toHarvestResponse(createdHarvest));
    }
}
