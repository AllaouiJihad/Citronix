package com.example.citronix.service;

import com.example.citronix.model.Field;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FieldService {
    Field save(Field field,Long farmId);
    Field update(Long id, Field field);
    void delete(Long id);
    Field getFieldById(Long id);
    List<Field> getAll();
}
