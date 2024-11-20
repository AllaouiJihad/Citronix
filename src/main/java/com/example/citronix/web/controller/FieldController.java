package com.example.citronix.web.controller;

import com.example.citronix.model.Field;
import com.example.citronix.service.FieldService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/field")
public class FieldController {

    FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping("/save")
    public ResponseEntity<Field> createField(@Valid @RequestBody Field field){
        Field savedField = fieldService.save(field);
        return new ResponseEntity<Field>(savedField, HttpStatus.CREATED);

    }
}
