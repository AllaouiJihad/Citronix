package com.example.citronix.repository;

import com.example.citronix.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface FarmRepository extends JpaRepository<Farm,Long> {
    @Transactional
    @Modifying
    @Query("update Farm f set f.name = ?1, f.location = ?2, f.area = ?3, f.creationDate = ?4 where f.name is not null")
    int updateNameAndLocationAndAreaAndCreationDateByNameNotNull(String name, String location, Double area, LocalDate creationDate);
}
