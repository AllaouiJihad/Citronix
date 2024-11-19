package com.example.citronix.web.controller;

import com.example.citronix.model.Farm;
import com.example.citronix.service.FarmService;
import com.example.citronix.web.VM.FarmRequestDTO;
import com.example.citronix.web.VM.FarmResponseDTO;
import com.example.citronix.web.VM.mapper.FarmMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/farm")
public class FarmController {
    private FarmService farmService;
    @Autowired
    private FarmMapper farmMapper;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping("/save")
    public ResponseEntity<FarmResponseDTO> createFarm(@Valid @RequestBody FarmRequestDTO farm){

        Farm savedFarm = farmService.save(farmMapper.toEntity(farm));
        FarmResponseDTO responseDTO = farmMapper.toResponseDTO(savedFarm);
        return new ResponseEntity<FarmResponseDTO>(responseDTO, HttpStatus.CREATED);
    }
}




