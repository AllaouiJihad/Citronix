package com.example.citronix.service;

import com.example.citronix.model.Harvest;
import com.example.citronix.model.enums.Season;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HarvestService {
    Harvest createHarvest(Long fieldId, Harvest harvest);
    Harvest findById(Long id);
    void delete(Long id);
    List<Harvest> getHarvestsBySeason(Season season);
}
