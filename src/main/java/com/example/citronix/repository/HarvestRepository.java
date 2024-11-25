package com.example.citronix.repository;

import com.example.citronix.model.Harvest;
import com.example.citronix.model.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest,Long> {
    List<Harvest> findBySeason(Season season);
}
