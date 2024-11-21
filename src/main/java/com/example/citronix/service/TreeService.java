package com.example.citronix.service;
import com.example.citronix.model.Tree;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TreeService {

    Tree save(Tree tree,Long fieldId);

    Tree update(Long id, Tree tree);

    void delete(Long id);
    Tree getTreeById(Long id);
    List<Tree> getAll();
}
