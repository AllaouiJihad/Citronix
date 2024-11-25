package com.example.citronix.repository;

import com.example.citronix.dto.SearchFarmDTO;
import com.example.citronix.model.Farm;

import java.util.List;

public interface FarmSearchRepository {
    List<Farm> findByCriteria(SearchFarmDTO searchFarmDTO);
}
