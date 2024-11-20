package com.example.citronix.web.VM.mapper;

import com.example.citronix.model.Farm;
import com.example.citronix.web.VM.farm.FarmRequestDTO;
import com.example.citronix.web.VM.farm.FarmResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    Farm toEntity(FarmRequestDTO dto);

    FarmResponseDTO toResponseDTO(Farm farm);


}
