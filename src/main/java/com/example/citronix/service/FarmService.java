package com.example.citronix.service;

import com.example.citronix.model.Farm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FarmService {

    Farm save(Farm farm);
    Farm update(Long id, Farm farm);
    void delete(Long id);
    Farm getFarmById(Long id);
    List<Farm> getAllFarms();
    List<Farm> searchFarms(String name, String location, Double minArea);
//    Double calculateTotalArea(Long farmId);
}
