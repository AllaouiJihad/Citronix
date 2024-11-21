package com.example.citronix.service.impl;

import com.example.citronix.exception.ResourceNotFoundException;
import com.example.citronix.model.Farm;
import com.example.citronix.model.Field;
import com.example.citronix.model.Tree;
import com.example.citronix.repository.TreeRepository;
import com.example.citronix.service.FieldService;
import com.example.citronix.service.TreeService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component(value = "treeImpl")
public class TreeServiceImpl implements TreeService {

    TreeRepository treeRepository;
    FieldService fieldService;

    public TreeServiceImpl(TreeRepository treeRepository, FieldService fieldService) {
        this.treeRepository = treeRepository;
        this.fieldService = fieldService;
    }

    @Override
    public Tree save(Tree tree,Long fieldId) {
        if (tree == null){
            throw new ResourceNotFoundException("The field must not be null.");
        }
        // Vérification de la période de plantation
        if (tree.getPlantingDate() == null || !isValidPlantationPeriod(tree.getPlantingDate())) {
            throw new IllegalArgumentException("Trees can only be planted between the months of March and May");
        }
        Field field = fieldService.getFieldById(fieldId);
        double maxDensity = field.getArea() / 1000 * 10;

        if (field.getTrees().size() >= maxDensity) {
            throw new IllegalArgumentException("The tree density exceeds the allowed limit of 10 trees per 1000 m².");
        }

        tree.setField(field);
        return treeRepository.save(tree);

    }

    @Override
    public Tree update(Long id, Tree tree) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Tree getTreeById(Long id) {
        return null;
    }

    @Override
    public List<Tree> getAll() {
        return null;
    }

    private boolean isValidPlantationPeriod(LocalDate plantationDate) {
        int month = plantationDate.getMonthValue();
        return month >= 3 && month <= 5;  // Mars (3), Avril (4), Mai (5)
    }
}
