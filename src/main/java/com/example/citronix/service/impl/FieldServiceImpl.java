package com.example.citronix.service.impl;

import com.example.citronix.exception.FarmSurfaceException;
import com.example.citronix.exception.FieldsSurbiggerThanFarmException;
import com.example.citronix.exception.ResourceNotFoundException;
import com.example.citronix.model.Farm;
import com.example.citronix.model.Field;
import com.example.citronix.repository.FieldRepository;
import com.example.citronix.service.FarmService;
import com.example.citronix.service.FieldService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component(value = "fieldImpl")
public class FieldServiceImpl implements FieldService {
    FieldRepository fieldRepository;
    FarmFieldHelperService farmFieldHelperService;

    public FieldServiceImpl(FieldRepository fieldRepository, FarmFieldHelperService farmFieldHelperService) {
        this.fieldRepository = fieldRepository;
        this.farmFieldHelperService = farmFieldHelperService;
    }

    @Override
    public Field save(Field field,Long farmId) {
        if (field == null) {
            throw new ResourceNotFoundException("The field must not be null.");
        }


        Farm farm = farmFieldHelperService.validateAndFetchFarm(farmId, field.getArea());
        if (farm.getArea()/2 < field.getArea()){
            throw new FarmSurfaceException("The field cannot exceed 50% of the farm's total area.");
        }
        if (farm.getFields().size() >= 10){
            throw new FarmSurfaceException("The farm cannot contain more than 10 fields");
        }
        field.setFarm(farm);
        return fieldRepository.save(field);
    }

    @Override
    public Field update(Long fieldId, Field field) {

        Field existingField = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new ResourceNotFoundException("Field with ID " + fieldId + " not found"));
        Farm farm = existingField.getFarm();
        if (farm == null) {
            throw new IllegalStateException("The field is not associated with any farm.");
        }

        double totalFieldAreaWithoutCurrent = farm.getFields().stream()
                .filter(field1 -> !field1.getId().equals(fieldId))
                .mapToDouble(Field::getArea)
                .sum();

        if (totalFieldAreaWithoutCurrent + field.getArea() > farm.getArea()) {
            throw new FieldsSurbiggerThanFarmException("The updated field area exceeds the total farm area.");
        }

        existingField.setArea(field.getArea());

        return fieldRepository.save(existingField);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Field getFieldById(Long id) {

        return fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Field not found"));
    }

    @Override
    public List<Field> getAll() {
        return fieldRepository.findAll();
    }
}
