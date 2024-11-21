package com.example.citronix.web.controller;

import com.example.citronix.model.Field;
import com.example.citronix.service.FieldService;
import com.example.citronix.web.VM.field.FieldRequestVm;
import com.example.citronix.web.VM.field.FieldResponseVm;
import com.example.citronix.web.VM.mapper.FarmMapper;
import com.example.citronix.web.VM.mapper.FieldMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/field")
public class FieldController {

    FieldService fieldService;
    FieldMapper fieldMapper;
    @Autowired
    FarmMapper farmMapper;


    public FieldController(FieldService fieldService, FieldMapper fieldMapper) {
        this.fieldService = fieldService;
        this.fieldMapper = fieldMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<FieldResponseVm> createField(@Valid @RequestBody FieldRequestVm fieldRequestVm){
        Field field = fieldMapper.toEntity(fieldRequestVm);
        Field savedField = fieldService.save(field, fieldRequestVm.getFarmId());
        FieldResponseVm response = fieldMapper.toResponseDTO(savedField);
        response.setFarm(farmMapper.toDto(field.getFarm()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FieldResponseVm> updateField(@Valid @RequestBody FieldRequestVm fieldRequestVm, @PathVariable Long id){
        Field updatedField = fieldMapper.toEntity(fieldRequestVm);
        Field field = fieldService.update(id,updatedField);
        FieldResponseVm response = fieldMapper.toResponseDTO(field);
        response.setFarm(farmMapper.toDto(field.getFarm()));
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/getField/{id}")
    public ResponseEntity<FieldResponseVm> getByID(@PathVariable Long id){
        Field field =fieldService.getFieldById(id);
        FieldResponseVm fieldResponseVm = fieldMapper.toResponseDTO(field);
        fieldResponseVm.setFarm(farmMapper.toDto(field.getFarm()));
        return new  ResponseEntity<>(fieldResponseVm,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<FieldResponseVm>> getAll(){
        List<Field> fields = fieldService.getAll();
        List<FieldResponseVm> response = fields.stream()
                .map(field -> {
                    FieldResponseVm fieldResponseVm = fieldMapper.toResponseDTO(field);
                    fieldResponseVm.setFarm(farmMapper.toDto(field.getFarm()));
                    return fieldResponseVm;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
