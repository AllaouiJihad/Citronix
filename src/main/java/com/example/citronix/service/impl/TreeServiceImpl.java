package com.example.citronix.service.impl;

import com.example.citronix.exception.ResourceNotFoundException;
import com.example.citronix.model.Tree;
import com.example.citronix.repository.TreeRepository;
import com.example.citronix.service.TreeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "treeImpl")
public class TreeServiceImpl implements TreeService {

    TreeRepository treeRepository;

    public TreeServiceImpl(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public Tree save(Tree tree) {
        if (tree == null){
            throw new ResourceNotFoundException("The field must not be null.");
        }
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
}
