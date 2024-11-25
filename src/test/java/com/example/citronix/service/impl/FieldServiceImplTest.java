package com.example.citronix.service.impl;

import com.example.citronix.exception.FarmSurfaceException;
import com.example.citronix.exception.ResourceNotFoundException;
import com.example.citronix.model.Farm;
import com.example.citronix.model.Field;
import com.example.citronix.repository.FieldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class FieldServiceImplTest {
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
    void testSave_Success() {
        // Arrange
        Field field = new Field();
        field.setArea(10.0);

        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100.0);

        when(farmFieldHelperService.validateAndFetchFarm(1L, 10.0)).thenReturn(farm);
        when(fieldRepository.save(field)).thenReturn(field);


        Field savedField = fieldService.save(field, 1L);

        assertNotNull(savedField);
        verify(fieldRepository, times(1)).save(field);
    }

    @Test
    void testSave_FieldExceedsFarmLimit() {
        // Arrange
        Field field = new Field();
        field.setArea(60.0);

        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100.0);

        when(farmFieldHelperService.validateAndFetchFarm(1L, 60.0)).thenReturn(farm);

        // Act & Assert
        assertThrows(FarmSurfaceException.class, () -> fieldService.save(field, 1L));
    }
    @Test
    void testUpdate_Success() {
        // Arrange
        Field existingField = new Field();
        existingField.setId(1L);
        existingField.setArea(20.0);

        Field updatedField = new Field();
        updatedField.setArea(30.0);

        Farm farm = new Farm();
        farm.setArea(100.0);
        farm.setFields(List.of(existingField));

        existingField.setFarm(farm);

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(existingField));
        when(fieldRepository.save(existingField)).thenReturn(existingField);

        // Act
        Field result = fieldService.update(1L, updatedField);

        // Assert
        assertNotNull(result);
        assertEquals(30.0, result.getArea());
        verify(fieldRepository, times(1)).save(existingField);
    }

    @Test
    void testDelete_Success() {
        // Arrange
        Field field = new Field();
        field.setId(1L);

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));

        // Act
        fieldService.delete(1L);

        // Assert
        verify(fieldRepository, times(1)).delete(field);
    }

    @Test
    void testGetFieldById_NotFound() {
        // Arrange
        when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> fieldService.getFieldById(1L));
    }



}
