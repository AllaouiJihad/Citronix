package com.example.citronix.web.controller;

import com.example.citronix.model.Farm;
import com.example.citronix.service.FarmService;
import com.example.citronix.web.VM.farm.FarmRequestDTO;
import com.example.citronix.web.VM.farm.FarmResponseDTO;
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
    public ResponseEntity<FarmResponseDTO> createFarm(@Valid @RequestBody FarmRequestDTO farm){

        Farm savedFarm = farmService.save(farmMapper.toEntity(farm));
        return new ResponseEntity<FarmResponseDTO>(farmMapper.toResponseDTO(savedFarm), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FarmResponseDTO> updateFarm(@PathVariable Long id,@Valid @RequestBody FarmRequestDTO farmRequestDTO){
        Farm updatedFarm = farmService.update(id,farmMapper.toEntity(farmRequestDTO));
        return new ResponseEntity<FarmResponseDTO>(farmMapper.toResponseDTO(updatedFarm),HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FarmResponseDTO>> getFarms(){
        List<Farm> farms = farmService.getAllFarms();
        List<FarmResponseDTO> allfarms =  farms.stream().map(farm -> farmMapper.toResponseDTO(farm)).collect(Collectors.toList());
        return  ResponseEntity.ok(allfarms);
    }
}




