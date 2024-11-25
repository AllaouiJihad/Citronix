package com.example.citronix.web.VM.mapper;

import com.example.citronix.model.Farm;
import com.example.citronix.web.VM.farm.FarmRequestVm;
import com.example.citronix.web.VM.farm.FarmResponseVm;
import com.example.citronix.web.VM.farm.FarmVm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    Farm toEntity(FarmRequestVm dto);

    FarmResponseVm toResponseDTO(Farm farm);

    FarmVm toDto(Farm farm);


}
