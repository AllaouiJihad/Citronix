package com.example.citronix.service.impl;

import com.example.citronix.exception.FieldSurfaceExceedsFarmException;
import com.example.citronix.exception.FieldsSurbiggerThanFarmException;
import com.example.citronix.model.Farm;
import com.example.citronix.model.Field;
import com.example.citronix.repository.FarmRepository;
import com.example.citronix.service.FarmService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FarmFieldHelperService {
    private FarmRepository farmRepository;

    public FarmFieldHelperService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    public Farm validateAndFetchFarm(Long farmId, Double fieldArea) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new EntityNotFoundException("Farm not found"));
        double totalFieldArea = farm.getFields().stream().mapToDouble(Field::getArea).sum();
        if (totalFieldArea + fieldArea > farm.getArea()) {
            throw new FieldsSurbiggerThanFarmException("La superficie totale des champs dépasse celle de la ferme.");
        }
        return farm;
    }

}
