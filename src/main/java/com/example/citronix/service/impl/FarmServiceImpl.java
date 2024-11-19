package com.example.citronix.service.impl;

import com.example.citronix.exception.FarmSurfacePositiveException;
import com.example.citronix.exception.FieldEmptyException;
import com.example.citronix.exception.FieldSurfaceExceedsFarmException;
import com.example.citronix.model.Farm;
import com.example.citronix.model.Field;
import com.example.citronix.repository.FarmRepository;
import com.example.citronix.service.FarmService;
import com.example.citronix.service.FieldService;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private FieldService fieldService;

    public FarmServiceImpl(FarmRepository farmRepository, FieldService fieldService) {
        this.farmRepository = farmRepository;
        this.fieldService = fieldService;
    }

    @Override
    public Farm save(Farm farm) {
        if (farm.getArea() <= 0) {
            throw new FarmSurfacePositiveException("La superficie de la ferme doit être positive.");
        }

        if (farm.getFields() == null || farm.getFields().isEmpty()) {
            throw new FieldEmptyException("Une ferme doit contenir au moins un champ.");
        }

        double totalFieldArea = farm.getFields().stream()
                .mapToDouble(Field::getArea)
                .sum();

        if (totalFieldArea >= farm.getArea()) {
            throw new FieldSurfaceExceedsFarmException(
                    "La superficie totale des champs doit etre strictement inferieure  de la ferme.");
        }



        farm.getFields().forEach(field -> field.setFarm(farm));

        return farmRepository.save(farm);
    }


    @Override
    public Farm update(Long id, Farm farm) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Farm getFarmById(Long id) {
        return null;
    }

    @Override
    public List<Farm> getAllFarms() {
        return null;
    }

    @Override
    public List<Farm> searchFarms(String name, String location, Double minArea) {
        return null;
    }

    public List<Farm> getFarmsWithSumFieldsAreaLessThan4000() {
        List<Farm> farms = farmRepository.findAll();

        farms.removeIf(farm -> farm.getFields().stream().mapToDouble(Field::getArea).sum() >= 4000);
        return farms;

    }
}