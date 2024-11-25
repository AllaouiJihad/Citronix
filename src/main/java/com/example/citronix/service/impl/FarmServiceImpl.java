package com.example.citronix.service.impl;

import com.example.citronix.dto.SearchFarmDTO;
import com.example.citronix.exception.FarmSurfaceException;
import com.example.citronix.model.Farm;
import com.example.citronix.model.Field;
import com.example.citronix.repository.FarmRepository;
import com.example.citronix.repository.FarmSearchRepository;
import com.example.citronix.service.FarmService;
import com.example.citronix.service.FieldService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component(value = "farmImpl")
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private FieldService fieldService;
    FarmSearchRepository farmSearchRepository;


    public FarmServiceImpl(FarmRepository farmRepository, FieldService fieldService, FarmSearchRepository farmSearchRepository) {
        this.farmRepository = farmRepository;
        this.fieldService = fieldService;
        this.farmSearchRepository = farmSearchRepository;
    }

    @Override
    public Farm save(Farm farm) {
        if (farm.getArea() <= 0) {
            throw new FarmSurfaceException("The farm area must be positive.");
        }
        if (farm.getArea() < 1000){
            throw new FarmSurfaceException("The farm area must bz bigger than 1000m²");
        }

        /*if (farm.getFields() == null || farm.getFields().isEmpty()) {
            throw new FieldEmptyException("Une ferme doit contenir au moins un champ.");
        }*/

       /* double totalFieldArea = farm.getFields().stream()
                .mapToDouble(Field::getArea)
                .sum();

        if (totalFieldArea >= farm.getArea()) {
            throw new FieldSurfaceExceedsFarmException(
                    "La superficie totale des champs doit etre strictement inferieure  de la ferme.");
        }*/



/*
        farm.getFields().forEach(field -> field.setFarm(farm));
*/

        return farmRepository.save(farm);
    }


    @Override
    public Farm update(Long id, Farm farm) {
        if (farm.getName() == null || farm.getName().isEmpty()) {
            throw new IllegalArgumentException("Farm name cannot be null or empty");
        }

        if (farm.getArea() <= 0) {
            throw new IllegalArgumentException("Farm area must be greater than 0");
        }

        if (farm.getCreationDate() != null && farm.getCreationDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Creation date cannot be in the future");
        }
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm not found"));
        existingFarm.setName(farm.getName());
        existingFarm.setLocation(farm.getLocation());
        existingFarm.setArea(farm.getArea());
        return farmRepository.save(existingFarm);

    }

    @Override
    public void delete(Long id) {
        Farm farmToDelete = getFarmById(id);
        farmRepository.delete(farmToDelete);

    }

    @Override
    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm not found"));
    }

    @Override
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @Override
    public List<Farm> search(SearchFarmDTO searchFarmDTO) {
        return farmSearchRepository.findByCriteria(searchFarmDTO);
    }
    public List<Farm> getFarmsWithSumFieldsAreaLessThan4000() {
        List<Farm> farms = farmRepository.findAll();

        farms.removeIf(farm -> farm.getFields().stream().mapToDouble(Field::getArea).sum() >= 4000);
        return farms;

    }
}
