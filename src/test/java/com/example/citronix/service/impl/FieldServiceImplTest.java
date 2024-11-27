package com.example.citronix.service.impl;

import com.example.citronix.exception.FarmSurfaceException;
import com.example.citronix.exception.FieldsSurbiggerThanFarmException;
import com.example.citronix.exception.ResourceNotFoundException;
import com.example.citronix.model.Farm;
import com.example.citronix.model.Field;
import com.example.citronix.repository.FieldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FieldServiceImplTest {

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmFieldHelperService farmFieldHelperService;

    @InjectMocks
    private FieldServiceImpl fieldService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldSaveField_WhenValidFieldAndFarm() {
        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100.0);
        farm.setFields(new ArrayList<>());
        Field field = new Field();
        field.setArea(40.0);

        when(farmFieldHelperService.validateAndFetchFarm(1L, 40.0)).thenReturn(farm);
        when(fieldRepository.save(any(Field.class))).thenReturn(field);

        Field savedField = fieldService.save(field, 1L);

        assertNotNull(savedField);
        verify(fieldRepository, times(1)).save(field);
    }

    @Test
    void save_ShouldThrowException_WhenFieldIsNull() {
        assertThrows(ResourceNotFoundException.class, () -> fieldService.save(null, 1L));
    }

    @Test
    void save_ShouldThrowException_WhenFieldExceedsFarmAreaLimit() {
        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100.0);
        farm.setFields(new ArrayList<>());
        Field field = new Field();
        field.setArea(60.0);

        when(farmFieldHelperService.validateAndFetchFarm(1L, 60.0)).thenReturn(farm);

        assertThrows(FarmSurfaceException.class, () -> fieldService.save(field, 1L));
    }

    @Test
    void save_ShouldThrowException_WhenFarmExceedsFieldLimit() {
        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100.0);
        farm.setFields(new ArrayList<>(List.of(new Field(), new Field(), new Field(), new Field(),
                new Field(), new Field(), new Field(), new Field(), new Field(), new Field())));

        Field field = new Field();
        field.setArea(10.0);

        when(farmFieldHelperService.validateAndFetchFarm(1L, 10.0)).thenReturn(farm);

        assertThrows(FarmSurfaceException.class, () -> fieldService.save(field, 1L));
    }

    // Tests pour update
    @Test
    void update_ShouldUpdateField_WhenValidData() {
        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100.0);
        Field field = new Field();
        field.setId(1L);
        field.setArea(20.0);
        farm.setFields(List.of(field));

        Field updatedField = new Field();
        updatedField.setArea(30.0);

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));

        Field result = fieldService.update(1L, updatedField);

        assertEquals(30.0, result.getArea());
        verify(fieldRepository, times(1)).save(field);
    }

    @Test
    void update_ShouldThrowException_WhenFieldNotFound() {
        when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

        Field updatedField = new Field();
        updatedField.setArea(30.0);

        assertThrows(ResourceNotFoundException.class, () -> fieldService.update(1L, updatedField));
    }

    @Test
    void update_ShouldThrowException_WhenUpdatedFieldExceedsFarmArea() {
        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100.0);
        Field field1 = new Field();
        field1.setId(1L);
        field1.setArea(30.0);
        Field field2 = new Field();
        field2.setId(2L);
        field2.setArea(40.0);
        farm.setFields(List.of(field1, field2));

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field1));

        Field updatedField = new Field();
        updatedField.setArea(60.0);

        assertThrows(FieldsSurbiggerThanFarmException.class, () -> fieldService.update(1L, updatedField));
    }

    // Tests pour delete
    @Test
    void delete_ShouldDeleteField_WhenFieldExists() {
        Field field = new Field();
        field.setId(1L);

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));

        fieldService.delete(1L);

        verify(fieldRepository, times(1)).delete(field);
    }

    @Test
    void delete_ShouldThrowException_WhenFieldNotFound() {
        when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> fieldService.delete(1L));
    }

    // Tests pour getFieldById
    @Test
    void getFieldById_ShouldReturnField_WhenFieldExists() {
        Field field = new Field();
        field.setId(1L);

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));

        Field foundField = fieldService.getFieldById(1L);

        assertNotNull(foundField);
        assertEquals(1L, foundField.getId());
    }

    @Test
    void getFieldById_ShouldThrowException_WhenFieldNotFound() {
        when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> fieldService.getFieldById(1L));
    }

    // Tests pour getAll
    @Test
    void getAll_ShouldReturnAllFields() {
        List<Field> fields = List.of(new Field(), new Field());
        when(fieldRepository.findAll()).thenReturn(fields);

        List<Field> result = fieldService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
