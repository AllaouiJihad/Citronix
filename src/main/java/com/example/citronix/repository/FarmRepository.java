package com.example.citronix.repository;

import com.example.citronix.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FarmRepository extends JpaRepository<Farm,Long> {
    // Recherche multicritère ou par attributs spécifiques
    List<Farm> findByNameContainingIgnoreCase(String name);
    List<Farm> findByLocationContainingIgnoreCase(String location);

    @Query("SELECT f FROM Farm f WHERE f.area > :minArea")
    List<Farm> findFarmsWithMinArea(@Param("minArea") Double minArea);
}
