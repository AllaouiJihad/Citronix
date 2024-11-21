package com.example.citronix.web.controller;

import com.example.citronix.model.Farm;
import com.example.citronix.service.FarmService;
import com.example.citronix.web.VM.farm.FarmRequestVm;
import com.example.citronix.web.VM.farm.FarmResponseVm;
import com.example.citronix.web.VM.mapper.FarmMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/farm")
public class FarmController {
    @Qualifier(value = "farmImpl")
    private FarmService farmService;
    @Autowired
    private FarmMapper farmMapper;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping("/save")
    public ResponseEntity<FarmResponseVm> createFarm(@Valid @RequestBody FarmRequestVm farm){

        Farm savedFarm = farmService.save(farmMapper.toEntity(farm));
        return new ResponseEntity<FarmResponseVm>(farmMapper.toResponseDTO(savedFarm), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FarmResponseVm> updateFarm(@PathVariable Long id, @Valid @RequestBody FarmRequestVm farmRequestDTO){
        Farm updatedFarm = farmService.update(id,farmMapper.toEntity(farmRequestDTO));
        return new ResponseEntity<FarmResponseVm>(farmMapper.toResponseDTO(updatedFarm),HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FarmResponseVm>> getFarms(){
        List<Farm> farms = farmService.getAllFarms();
        List<FarmResponseVm> allfarms =  farms.stream().map(farm -> farmMapper.toResponseDTO(farm)).collect(Collectors.toList());
        return  ResponseEntity.ok(allfarms);
    }

    @GetMapping("/getFarm/{id}")
    public ResponseEntity<FarmResponseVm> getFarm(@PathVariable Long id){
        FarmResponseVm farm = farmMapper.toResponseDTO(farmService.getFarmById(id));
        return ResponseEntity.ok(farm);
    }
}




