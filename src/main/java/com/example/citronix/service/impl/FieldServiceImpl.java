package com.example.citronix.service.impl;

import com.example.citronix.exception.ResourceNotFoundException;
import com.example.citronix.model.Field;
import com.example.citronix.repository.FieldRepository;
import com.example.citronix.service.FieldService;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component(value = "fieldImpl")
public class FieldServiceImpl implements FieldService {
    FieldRepository fieldRepository;

    public FieldServiceImpl(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Override
    public Field save(Field field) {

        if (field == null){
            throw  new ResourceNotFoundException("field ne doit pas etre null");
        }
        return fieldRepository.save(field);
    }

    @Override
    public Field update(Long id, Field field) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Field getFieldById(Long id) {
        return null;
    }

    @Override
    public List<Field> getAll() {
        return null;
    }
}
