package com.example.citronix.repository;

import com.example.citronix.model.Harvest;
import com.example.citronix.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    boolean existsByHarvest(Harvest harvest);
}
