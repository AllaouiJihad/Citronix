package com.example.citronix.web.VM.mapper;

import com.example.citronix.model.Farm;
import com.example.citronix.web.VM.FarmRequestDTO;
import com.example.citronix.web.VM.FarmResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    Farm toEntity(FarmRequestDTO dto);

    @Mapping(source = "farm.name", target = "name")
    FarmResponseDTO toResponseDTO(Farm farm);


}
