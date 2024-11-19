package com.example.citronix.service;

import com.example.citronix.model.Field;

import java.util.List;

public interface FieldService {
    Field save(Field field);
    Field update(Long id, Field field);
    void delete(Long id);
    Field getFieldById(Long id);
    List<Field> getAll();
}
