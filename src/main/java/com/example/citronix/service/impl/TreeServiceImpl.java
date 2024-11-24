package com.example.citronix.service.impl;

import com.example.citronix.exception.InvalidCredentialsException;
import com.example.citronix.exception.ResourceNotFoundException;
import com.example.citronix.exception.TreeException;
import com.example.citronix.exception.TreeNotFoundException;
import com.example.citronix.model.Farm;
import com.example.citronix.model.Field;
import com.example.citronix.model.Tree;
import com.example.citronix.repository.TreeRepository;
import com.example.citronix.service.FieldService;
import com.example.citronix.service.TreeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        if (tree.getPlantingDate() == null || !isValidPlantationPeriod(tree.getPlantingDate())) {
            throw new TreeException("Trees can only be planted between the months of March and May");
        }
        Field field = fieldService.getFieldById(fieldId);
        double maxDensity = field.getArea() / 1000 * 10;

        if (field.getTrees().size() >= maxDensity) {
            throw new TreeException("The tree density exceeds the allowed limit of 10 trees per 1000 m².");
        }

        tree.setField(field);
        return treeRepository.save(tree);

    }

    @Override
    public Tree update(Long id, Tree tree) {
        if (id == null || tree == null) {
            throw new InvalidCredentialsException("Id and tree information are required for update.");
        }

        Tree existingTree = getTreeById(id);

        if (tree.getPlantingDate() != null && !isValidPlantationPeriod(tree.getPlantingDate())) {
            throw new TreeException("Trees can only be planted between the months of March and May");
        }

        if (tree.getPlantingDate() != null) {
            existingTree.setPlantingDate(tree.getPlantingDate());
        }
        if (tree.getField() != null) {
            Field field = fieldService.getFieldById(tree.getField().getId());
            existingTree.setField(field);
        }

        return treeRepository.save(existingTree);
    }

    @Override
    public void delete(Long id) {
        Tree treeToDelete = getTreeById(id);
        treeRepository.delete(treeToDelete);
    }

    @Override
    public Tree getTreeById(Long id) {
        if (id == null){
            throw new InvalidCredentialsException("id is required");
        }
        return treeRepository.findById(id)
                .orElseThrow(()-> new TreeNotFoundException("tree not found"));
    }

    @Override
    public List<Tree> getAll() {
        return treeRepository.findAll();
    }

    @Override
    public Page<Tree> getAllTreesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return treeRepository.findAll(pageable);    }

    private boolean isValidPlantationPeriod(LocalDate plantationDate) {
        int month = plantationDate.getMonthValue();
        return month >= 3 && month <= 5;
    }
}
