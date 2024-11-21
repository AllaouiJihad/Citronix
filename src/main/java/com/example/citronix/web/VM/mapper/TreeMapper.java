package com.example.citronix.web.VM.mapper;

import com.example.citronix.model.Tree;
import com.example.citronix.web.VM.tree.TreeRequestVm;
import com.example.citronix.web.VM.tree.TreeresponseVm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TreeMapper {

    Tree toEntity(TreeRequestVm treeRequestVm);
    TreeresponseVm toResponseVm(Tree tree);
}
