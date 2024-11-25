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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/harvest")
public class HarvestController {

    HarvestService harvestService;
    HarvestMapper harvestMapper;

    public HarvestController(HarvestService harvestService, HarvestMapper harvestMapper) {
        this.harvestService = harvestService;
        this.harvestMapper = harvestMapper;
    }

    @PostMapping("/save/{fieldId}")
    public ResponseEntity<HarvestResponseVm> createHarvest(@PathVariable Long fieldId, @Valid @RequestBody HarvestRequestVm harvestRequestVm) {
        Harvest harvest = harvestMapper.toEntity(harvestRequestVm);
        Harvest createdHarvest = harvestService.createHarvest(fieldId,harvest);

        return ResponseEntity.status(HttpStatus.CREATED).body(harvestMapper.toHarvestResponse(createdHarvest));
    }

    @GetMapping("/getHarvest/{id}")
    public ResponseEntity<HarvestResponseVm> getHarvest(@PathVariable Long id){
        Harvest harvest = harvestService.findById(id);
        HarvestResponseVm response = harvestMapper.toHarvestResponse(harvest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getALl")
    public ResponseEntity<List<HarvestResponseVm>> getALl(){
        List<Harvest> harvests = harvestService.getAll();
        List<HarvestResponseVm> responseVms = harvests.stream().map(harvest -> harvestMapper.toHarvestResponse(harvest)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseVms);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        harvestService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Harvest deleted successfully");
    }
}
