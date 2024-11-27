package com.example.citronix.service.impl;

import com.example.citronix.exception.HarvestNotFoundException;
import com.example.citronix.exception.InvalidCredentialsException;
import com.example.citronix.exception.TreeException;
import com.example.citronix.model.Field;
import com.example.citronix.model.Harvest;
import com.example.citronix.model.HarvestDetail;
import com.example.citronix.model.Tree;
import com.example.citronix.model.enums.Season;
import com.example.citronix.repository.HarvestDatailRepository;
import com.example.citronix.repository.HarvestRepository;
import com.example.citronix.service.FieldService;
import com.example.citronix.service.HarvestService;
import com.example.citronix.utils.SeasonUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component(value = "harvestImpl")
public class HarvestServiceImpl implements HarvestService {

    private HarvestRepository harvestRepository;
    private HarvestDatailRepository harvestDatailRepository;
    private FieldService fieldService;

    public HarvestServiceImpl(HarvestRepository harvestRepository, HarvestDatailRepository harvestDatailRepository, FieldService fieldService) {
        this.harvestRepository = harvestRepository;
        this.harvestDatailRepository = harvestDatailRepository;
        this.fieldService = fieldService;
    }

    @Transactional
    @Override
    public Harvest createHarvest(Long fieldId, Harvest harvest) {
       Field field = fieldService.getFieldById(fieldId);
       Season season = harvest.getSeason();

    }

    @Override
    public Harvest findById(Long id) {

        if (id == null){
            throw new InvalidCredentialsException("harvest ID is Required");
        }
        return harvestRepository.findById(id)
                .orElseThrow(()-> new HarvestNotFoundException("harvest Not Found"));
    }

    @Override
    public void delete(Long id) {
        Harvest harvestToDelete = findById(id);
        harvestRepository.delete(harvestToDelete);
    }

    @Override
    public List<Harvest> getHarvestsBySeason(Season season) {

        return harvestRepository.findBySeason(season);

    }

    @Override
    public List<Harvest> getAll() {
        return harvestRepository.findAll();

    }
}
