package com.example.citronix.repository;

import com.example.citronix.model.HarvestDetail;
import com.example.citronix.model.Tree;
import com.example.citronix.model.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestDatailRepository extends JpaRepository<HarvestDetail,Long> {
    boolean existsByTreeAndHarvestSeason(Tree tree, Season season);

    @Query("SELECT CASE WHEN COUNT(hd) > 0 THEN TRUE ELSE FALSE END " +
            "FROM HarvestDetail hd " +
            "JOIN hd.harvest h " +
            "WHERE hd.tree = :tree AND h.season = :season AND YEAR(h.harvestDate) = :year")
    boolean existsByTreeAndSeasonAndYear(@Param("tree") Tree tree,
                                         @Param("season") Season season,
                                         @Param("year") int year);
}
