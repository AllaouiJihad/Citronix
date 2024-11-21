package com.example.citronix.repository;

import com.example.citronix.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<Tree,Long> {
}
