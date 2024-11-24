package com.example.citronix.web.controller;

import com.example.citronix.model.Tree;
import com.example.citronix.service.TreeService;
import com.example.citronix.web.VM.mapper.TreeMapper;
import com.example.citronix.web.VM.tree.TreeRequestVm;
import com.example.citronix.web.VM.tree.TreeResponseVm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tree")
public class TreeController {

    private TreeService treeService;
    private TreeMapper treeMapper;

    public TreeController(TreeService treeService, TreeMapper treeMapper) {
        this.treeService = treeService;
        this.treeMapper = treeMapper;
    }

    @PostMapping("/save/{fieldId}")
    public ResponseEntity<TreeResponseVm> saveTree(@PathVariable Long fieldId, @RequestBody @Valid TreeRequestVm treeRequestVm) {
        Tree tree = treeMapper.toEntity(treeRequestVm);
        Tree savedTree = treeService.save(tree,fieldId);
        TreeResponseVm treeResponseVM = treeMapper.toResponseVm(savedTree);
        return ResponseEntity.status(HttpStatus.CREATED).body(treeResponseVM);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<TreeResponseVm> updateTree(@PathVariable Long id, @RequestBody @Valid TreeRequestVm treeRequestVm){
        Tree tree = treeMapper.toEntity(treeRequestVm);
        Tree updatedTree = treeService.update(id, tree);
        TreeResponseVm treeResponseVm = treeMapper.toResponseVm(updatedTree);
        return ResponseEntity.status(HttpStatus.OK).body(treeResponseVm);
    }

    @GetMapping("/getTree/{id}")
    public ResponseEntity<TreeResponseVm> getTree(@PathVariable Long id){
        Tree getedTree = treeService.getTreeById(id);
        TreeResponseVm treeResponseVm = treeMapper.toResponseVm(getedTree);
        return ResponseEntity.status(HttpStatus.OK).body(treeResponseVm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTree(@PathVariable Long id) {
        treeService.delete(id);
        return ResponseEntity.ok("Tree deleted successfully");
    }
    @GetMapping("/getAll")
    public ResponseEntity<Page<TreeResponseVm>> getTreesWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Tree> treePage = treeService.getAllTreesPaginated(page,size);
        List<TreeResponseVm> treeResponseVMS = treePage.stream().map(treeMapper::toResponseVm).toList();
        Page<TreeResponseVm> treeResponseVMPage = new PageImpl<>(treeResponseVMS, treePage.getPageable(), treePage.getTotalElements());

        return ResponseEntity.ok(treeResponseVMPage);
    }




}
