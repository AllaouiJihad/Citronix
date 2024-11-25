package com.example.citronix.web.VM.mapper;

import com.example.citronix.model.Harvest;
import com.example.citronix.web.VM.harvest.HarvestRequestVm;
import com.example.citronix.web.VM.harvest.HarvestResponseVm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    Harvest toEntity(HarvestRequestVm harvestRequestVm);

    HarvestResponseVm toHarvestResponse(Harvest harvest);
}
